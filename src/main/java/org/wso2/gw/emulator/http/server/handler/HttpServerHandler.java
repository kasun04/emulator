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

package org.wso2.gw.emulator.http.server.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import org.apache.log4j.Logger;
import org.wso2.gw.emulator.http.server.processors.HttpRequestResponseMatchingProcessor;
import org.wso2.gw.emulator.http.server.contexts.HttpServerProcessorContext;
import org.wso2.gw.emulator.http.server.contexts.HttpRequestContext;
import org.wso2.gw.emulator.http.server.processors.HttpRequestInformationProcessor;
import org.wso2.gw.emulator.http.server.contexts.HttpServerInformationContext;
import org.wso2.gw.emulator.http.server.processors.HttpResponseProcessor;

import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = Logger.getLogger(HttpServerHandler.class);
    private HttpRequestInformationProcessor httpRequestInformationProcessor;
    private HttpResponseProcessor httpResponseProcessor;
    private HttpServerInformationContext serverInformationContext;
    private HttpServerProcessorContext httpProcessorContext;
    private HttpRequestResponseMatchingProcessor requestResponseMatchingProcessor;

    public HttpServerHandler(HttpServerInformationContext serverInformationContext) {
        this.serverInformationContext = serverInformationContext;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpRequest) {
            // readingDelay(serverInformationContext.getReadingDelay());
            this.httpRequestInformationProcessor = new HttpRequestInformationProcessor();
            this.httpResponseProcessor = new HttpResponseProcessor();
            this.httpProcessorContext = new HttpServerProcessorContext();
            this.httpProcessorContext.setHttpRequestContext(new HttpRequestContext());
            this.httpProcessorContext.setServerInformationContext(serverInformationContext);
            HttpRequest httpRequest = (HttpRequest) msg;
            this.httpProcessorContext.setHttpRequest(httpRequest);
            if (HttpHeaders.is100ContinueExpected(httpRequest)) {
                send100Continue(ctx);
            }
            httpRequestInformationProcessor.process(httpProcessorContext);
        } else {
            if (msg instanceof HttpContent) {
                HttpContent httpContent = (HttpContent) msg;
                if (httpContent.content().isReadable()) {
                    httpProcessorContext.setHttpContent(httpContent);
                    httpRequestInformationProcessor.process(httpProcessorContext);
                }
            }

            if (msg instanceof LastHttpContent) {
                this.requestResponseMatchingProcessor = new HttpRequestResponseMatchingProcessor();
                this.requestResponseMatchingProcessor.process(httpProcessorContext);
                ctx.fireChannelReadComplete();
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        if (httpResponseProcessor != null) {
            // waitingDelay(consumerContext.getWritingDelay());
            this.httpResponseProcessor.process(httpProcessorContext);
            FullHttpResponse response = httpProcessorContext.getFinalResponse();
            if (httpProcessorContext.getHttpRequestContext().isKeepAlive()) {
                ctx.write(response);
            } else {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            }
        }
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Exception occurred while processing the response", cause);
        ctx.close();
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, CONTINUE);
        ctx.write(response);
    }

    private void readingDelay(int delay) {
        try {
            if (delay > 0) {
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            log.error("Exception occurred while processing the reading delay", e);
        }
    }

    private void waitingDelay(int delay) {
        try {
            if (delay > 0) {
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            log.error("Exception occurred while processing the waiting delay", e);
        }
    }
}
