/*
 * *
 *  * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *  *
 *  * WSO2 Inc. licenses this file to you under the Apache License,
 *  * Version 2.0 (the "License"); you may not use this file except
 *  * in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied.  See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 *
 */

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
import org.apache.log4j.Logger;
import org.wso2.gw.emulator.http.HTTPProtocolEmulator;

public class Emulator {

    private static final boolean SSL = System.getProperty("ssl") != null;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private static final Logger log = Logger.getLogger(Emulator.class);

    public static HTTPProtocolEmulator getHttpEmulator() {
        HTTPProtocolEmulator httpProtocolEmulator = new HTTPProtocolEmulator(new Emulator());
        return httpProtocolEmulator;
    }

    public void initialize(EmulatorType emulatorType) throws Exception {
        validateInput();
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
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new EmulatorInitializer(emulatorType, sslCtx));
            ChannelFuture f = b.bind(AbstractEmulatorContext.getHost(), AbstractEmulatorContext.getPort())
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
        log.info("Emulator shutdown successfully.......");
    }

    private void validateInput() {

        if(AbstractEmulatorContext.getHost() == null || AbstractEmulatorContext.getPort() == null) {
           log.error("Invalid host [" +AbstractEmulatorContext.getHost() +"] and port [" +AbstractEmulatorContext
                   .getPort() +"]");
        }
    }
}
