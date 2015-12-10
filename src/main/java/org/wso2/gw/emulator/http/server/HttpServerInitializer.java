package org.wso2.gw.emulator.http.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import org.wso2.gw.emulator.core.EmulatorType;
import org.wso2.gw.emulator.http.ChannelPipelineInitializer;
import org.wso2.gw.emulator.http.server.contexts.HttpServerInformationContext;

public class HttpServerInitializer {
    private static final boolean SSL = System.getProperty("ssl") != null;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private HttpServerInformationContext serverInformationContext;

    public HttpServerInitializer(HttpServerInformationContext serverInformationContext) {
        this.serverInformationContext = serverInformationContext;
    }

    public void initialize() throws Exception {
        final SslContext sslCtx = null;
        /*if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }*/
        // Configure the server.
        bossGroup = new NioEventLoopGroup(getCPUCoreSize());
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ChannelPipelineInitializer channelPipelineInitializer = new ChannelPipelineInitializer(sslCtx,
                                                                                                   EmulatorType.HTTP_CONSUMER);
            channelPipelineInitializer.setServerInformationContext(serverInformationContext);
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(channelPipelineInitializer);
            ChannelFuture f = serverBootstrap.bind(serverInformationContext.getServerConfigBuilderContext().getHost()
                    , serverInformationContext.getServerConfigBuilderContext().getPort())
                    .sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void shutdown() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    private int getCPUCoreSize() {
        return Runtime.getRuntime().availableProcessors();
    }
}
