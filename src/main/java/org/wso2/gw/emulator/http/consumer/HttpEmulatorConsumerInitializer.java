package org.wso2.gw.emulator.http.consumer;

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
import org.wso2.gw.emulator.http.dsl.HttpConsumerContext;

public class HttpEmulatorConsumerInitializer {
    private static final boolean SSL = System.getProperty("ssl") != null;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private HttpConsumerContext consumerContext;

    public HttpEmulatorConsumerInitializer(HttpConsumerContext consumerContext) {
        this.consumerContext = consumerContext;
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
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            ChannelPipelineInitializer channelPipelineInitializer = new ChannelPipelineInitializer(sslCtx,
                                                                                                   EmulatorType.TCP_CONSUMER);
            channelPipelineInitializer.setConsumerContext(consumerContext);
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(channelPipelineInitializer);
            ChannelFuture f = b.bind(consumerContext.getHost(), consumerContext.getPort())
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
}
