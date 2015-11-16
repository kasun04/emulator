package org.wso2.gw.emulator.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import org.wso2.gw.emulator.http.HTTPProtocolEmulator;

public class Emulator {

    private static final boolean SSL = System.getProperty("ssl") != null;
    private static EmulatorContext emulatorContext;

    public static AbstractProtocolEmulator getProtocolEmulator(String protocol) {

        emulatorContext = new EmulatorContext();
        if ("http".equals(protocol)) {
            return new HTTPProtocolEmulator(emulatorContext);
        }
        return null;
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
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new EmulatorInitializer(emulatorContext, sslCtx));
            // Start the server.
            ChannelFuture f = b.bind(emulatorContext.getHttpTransportInformation().getHost(), emulatorContext.getHttpTransportInformation().getPort())
                    .sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
