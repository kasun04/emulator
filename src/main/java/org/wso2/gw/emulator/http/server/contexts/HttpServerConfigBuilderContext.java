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

package org.wso2.gw.emulator.http.server.contexts;

import io.netty.channel.ChannelInboundHandlerAdapter;
import org.wso2.gw.emulator.dsl.contexts.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientRequestBuilderContext;

import java.util.Random;

public class HttpServerConfigBuilderContext extends AbstractConfigurationBuilderContext {

    private static HttpServerConfigBuilderContext config;
    private String host;
    private int port;
    private String context;
    private int readingDelay;
    private int writingDelay;
    private boolean randomConnectionClose;
    private ChannelInboundHandlerAdapter logicHandler;
    private int logicDelay;
    private boolean customProcessor;

    private static HttpServerConfigBuilderContext getInstance() {
        config = new HttpServerConfigBuilderContext();
        return config;
    }

    public boolean isCustomProcessor() {
        return customProcessor;
    }

    public static HttpServerConfigBuilderContext configure() {
        return getInstance();
    }

    public HttpServerConfigBuilderContext host(String host) {
        this.host = host;
        return this;
    }

    public HttpServerConfigBuilderContext port(int port) {
        this.port = port;
        return this;
    }

    public HttpServerConfigBuilderContext context(String context) {
        this.context = context;
        return this;
    }

    public HttpServerConfigBuilderContext readingDelay(int readingDelay) {
        this.readingDelay = readingDelay;
        return this;
    }

    public HttpServerConfigBuilderContext writingDelay(int writingDelay) {
        this.writingDelay = writingDelay;
        return this;
    }

    public HttpServerConfigBuilderContext logicDelay(int logicDelay) {
        this.logicDelay = logicDelay;
        return this;
    }

    public HttpServerConfigBuilderContext randomConnectionClose(boolean randomConnectionClose) {
        this.randomConnectionClose = randomConnectionClose;
        return this;
    }

    public HttpServerConfigBuilderContext logic(ChannelInboundHandlerAdapter logicHandler) {
        this.logicHandler = logicHandler;
        return this;
    }

    public HttpServerConfigBuilderContext withCustomProcessor(boolean customProcessor){
        this.customProcessor = customProcessor;
        return this;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
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

    public int getLogicDelay() {
        return logicDelay;
    }
}
