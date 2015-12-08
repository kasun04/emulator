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

package org.wso2.gw.emulator.http.dsl.consumer;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpVersion;
import org.wso2.gw.emulator.core.AbstractBuilderContext;
import org.wso2.gw.emulator.core.AbstractProtocolEmulator;
import org.wso2.gw.emulator.http.HTTPProtocolEmulator;

import java.util.HashMap;
import java.util.Map;


public class HttpServerBuilderContext extends AbstractBuilderContext {
    private String context;
    private int readingDelay;
    private int writingDelay;
    private boolean randomConnectionClose;
    private HttpVersion httpVersion;
    private IncomingMessage incoming;
    private ChannelInboundHandlerAdapter logicHandler;
    private Map<IncomingMessage, OutgoingMessage> inOutCorrelation = new HashMap<IncomingMessage, OutgoingMessage>();
    private HTTPProtocolEmulator httpProtocolEmulator;


    public HttpServerBuilderContext(HTTPProtocolEmulator httpProtocolEmulator) {
        this.httpProtocolEmulator = httpProtocolEmulator;
        this.httpVersion = HttpVersion.HTTP_1_1;
    }

    public HttpServerBuilderContext host(String host) {
        super.host(host);
        return this;
    }

    public HttpServerBuilderContext port(int port) {
        super.port(port);
        return this;
    }

    public HttpServerBuilderContext context(String context) {
        this.context = context;
        return this;
    }

    public HttpServerBuilderContext readingDelay(int readingDelay) {
        this.readingDelay = readingDelay;
        return this;
    }

    public HttpServerBuilderContext writingDelay(int writingDelay) {
        this.writingDelay = writingDelay;
        return this;
    }

    public HttpServerBuilderContext randomConnectionClose(boolean randomConnectionClose) {
        this.randomConnectionClose = randomConnectionClose;
        return this;
    }

    public HttpServerBuilderContext httpVersion(HttpVersion httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }

    public HttpServerBuilderContext given(testConfig config) {
        return this;
    }

    public HttpServerBuilderContext when(IncomingMessage incoming) {
        this.incoming = incoming;
        this.incoming.buildPathRegex(context);
        return this;
    }

    public HttpServerBuilderContext logic(ChannelInboundHandlerAdapter logicHandler) {
        this.logicHandler = logicHandler;
        return this;
    }

    public HttpServerBuilderContext respond(OutgoingMessage outgoing) {
        this.inOutCorrelation.put(incoming, outgoing);
        return this;
    }

    public AbstractProtocolEmulator operations() {
        return httpProtocolEmulator;
    }

    public String getContext() {
        return context;
    }

    public int getReadingDelay() {
        return readingDelay;
    }

    public int getWritingDelay() {
        return writingDelay;
    }

    public boolean isRandomConnectionClose() {
        return randomConnectionClose;
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public ChannelInboundHandlerAdapter getLogicHandler() {
        return logicHandler;
    }

    public IncomingMessage getIncoming() {
        return incoming;
    }

    public Map<IncomingMessage, OutgoingMessage> getInOutCorrelation() {
        return inOutCorrelation;
    }
}
