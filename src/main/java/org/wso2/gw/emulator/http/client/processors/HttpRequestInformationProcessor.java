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

package org.wso2.gw.emulator.http.client.processors;

import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import org.wso2.gw.emulator.http.client.contexts.HttpClientConfigBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientInformationContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientProcessorContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientRequestBuilderContext;
import org.wso2.gw.emulator.http.dsl.producer.HttpClientBuilderContext;
import org.wso2.gw.emulator.http.dsl.params.Cookie;
import org.wso2.gw.emulator.http.dsl.params.Header;
import org.wso2.gw.emulator.http.dsl.producer.IncomingMessage;
import org.wso2.gw.emulator.http.server.contexts.HttpServerConfigBuilderContext;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpRequestInformationProcessor extends AbstractClientProcessor {

    @Override
    public void process(HttpClientProcessorContext processorContext) {
        HttpClientConfigBuilderContext clientConfigBuilderContext = processorContext.getClientInformationContext()
                .getClientConfigBuilderContext();
        String uri = getURI(clientConfigBuilderContext.getHost(), clientConfigBuilderContext.getPort(),
                            processorContext.getRequest());
        URI requestUri = null;
        try {
            requestUri = new URI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        processorContext.getClientInformationContext().getClientConfigBuilderContext().host(requestUri.getHost());
        String scheme = requestUri.getScheme();

        if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
            System.err.println("Only HTTP(S) is supported.");
            //Need to log
        }
        HttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, processorContext.getRequest().getMethod(), requestUri.getRawPath());
        populateHeader(request, requestUri.getHost(), processorContext.getRequest());
        populateCookies(request, processorContext.getRequest());
        populateQueryParameters(request, processorContext.getRequest());
    }

    public HttpRequest populateHttpRequest(HttpClientBuilderContext clientBuilderContext, HttpClientRequestBuilderContext
            requestBuilderContext) throws Exception {

        String uri = getURI(clientBuilderContext.getHost(), clientBuilderContext.getPort(), requestBuilderContext);
        URI requestUri = new URI(uri);
        clientBuilderContext.host(requestUri.getHost());
        String scheme = requestUri.getScheme();

        if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
            System.err.println("Only HTTP(S) is supported.");
            //Need to log
        }
        HttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, requestBuilderContext.getMethod(), requestUri.getRawPath());
        populateHeader(request, requestUri.getHost(), requestBuilderContext);
        populateCookies(request, requestBuilderContext);
        populateQueryParameters(request, requestBuilderContext);
        return request;
    }

    private void populateHeader(HttpRequest request, String host, IncomingMessage incomingMessage) {
        request.headers().set(HttpHeaders.Names.HOST, host);
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
        request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);
        if (incomingMessage.getBody() != null) {
            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, incomingMessage.getBody().getBytes()
                    .length);
        }
        if (incomingMessage.getHeaders() != null) {
            for (Header header : incomingMessage.getHeaders()) {
                request.headers().set(header.getName(), header.getValue());
            }
        }
    }

    private void populateCookies(HttpRequest request, IncomingMessage incomingMessage) {
        if (incomingMessage.getCookies() != null) {
            DefaultCookie[] cookies = new DefaultCookie[incomingMessage.getCookies().size()];
            int i = 0;
            for (Cookie cookie : incomingMessage.getCookies()) {
                cookies[i++] = new DefaultCookie(cookie.getName(), cookie.getValue());
            }
            request.headers().set(
                    HttpHeaders.Names.COOKIE,
                    ClientCookieEncoder.STRICT.encode(cookies));
        }
    }

    private void populateQueryParameters(HttpRequest request, IncomingMessage incomingMessage) {

    }

    private String getURI(String host, int port, HttpClientRequestBuilderContext requestBuilderContext) {
        String path = requestBuilderContext.getPath();
        String uri = host + ":" + port;
        if (path.startsWith("/")) {
            uri = uri + path;
        } else {
            uri = uri + "/" + path;
        }
        return uri;
    }

}
