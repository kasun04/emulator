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

package org.wso2.gw.emulator.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import org.wso2.gw.emulator.http.dsl.HttpConsumerContext;

public class ChannelPipelineInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslCtx;
    private HttpConsumerContext consumerContext;

    public ChannelPipelineInitializer(SslContext sslCtx, HttpConsumerContext consumerContext) {
        this.sslCtx = sslCtx;
        this.consumerContext = consumerContext;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast("sslHandler", sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast(new HttpServerCodec());
        if (consumerContext.getLogicHandler() != null) {
            pipeline.addLast("logicHandler", consumerContext.getLogicHandler());
        }
        pipeline.addLast("httpResponseHandler", new HttpResponseProcessHandler(consumerContext));
    }
}
