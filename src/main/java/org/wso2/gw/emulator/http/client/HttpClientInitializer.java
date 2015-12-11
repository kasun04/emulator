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

package org.wso2.gw.emulator.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.wso2.gw.emulator.core.EmulatorType;
import org.wso2.gw.emulator.http.ChannelPipelineInitializer;
import org.wso2.gw.emulator.http.client.contexts.HttpClientConfigBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientInformationContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientProcessorContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientRequestBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientResponseBuilderContext;
import org.wso2.gw.emulator.http.client.processors.HttpRequestInformationProcessor;

import java.util.Map;

public class HttpClientInitializer {

    private HttpClientInformationContext clientInformationContext;
    private EventLoopGroup group;
    private Bootstrap bootstrap;

    public HttpClientInitializer(HttpClientInformationContext clientInformationContext) {
        this.clientInformationContext = clientInformationContext;
    }

    public void initialize() throws Exception {
        final boolean ssl = "https".equalsIgnoreCase(null);
        final SslContext sslCtx;
        if (ssl) {
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }

        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        ChannelPipelineInitializer channelPipelineInitializer = new ChannelPipelineInitializer(sslCtx,
                                                                                               EmulatorType.HTTP_CLIENT);
        channelPipelineInitializer.setClientInformationContext(clientInformationContext);
        bootstrap.group(group).channel(NioSocketChannel.class).handler(channelPipelineInitializer);

        for (Map.Entry<HttpClientRequestBuilderContext, HttpClientResponseBuilderContext> entry :
                clientInformationContext.getRequestResponseCorrelation().entrySet()) {
            HttpClientProcessorContext httpClientProcessorContext = new HttpClientProcessorContext();
            httpClientProcessorContext.setRequest(entry.getKey());
            httpClientProcessorContext.setExpectedResponse(entry.getValue());
            sendMessage(httpClientProcessorContext);
        }
    }

    private void sendMessage(HttpClientProcessorContext httpClientProcessorContext) throws Exception {
        try {
            new HttpRequestInformationProcessor().process(httpClientProcessorContext);
            HttpClientConfigBuilderContext clientConfigBuilderContext = httpClientProcessorContext.getClientInformationContext()
                    .getClientConfigBuilderContext();
            Channel ch = bootstrap.connect(clientConfigBuilderContext.getHost(), clientConfigBuilderContext.getPort()).sync().channel();
            ch.writeAndFlush(httpClientProcessorContext.getRequest());
            ch.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void shutdown() {
        group.shutdownGracefully();
    }
}
