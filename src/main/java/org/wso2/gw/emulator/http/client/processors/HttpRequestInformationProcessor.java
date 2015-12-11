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
import org.wso2.gw.emulator.http.client.contexts.HttpClientProcessorContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientRequestBuilderContext;
import org.wso2.gw.emulator.http.params.Cookie;
import org.wso2.gw.emulator.http.params.Header;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpRequestInformationProcessor extends AbstractClientProcessor {

    @Override
    public void process(HttpClientProcessorContext processorContext) {
        HttpClientConfigBuilderContext clientConfigBuilderContext = processorContext.getClientInformationContext()
                .getClientConfigBuilderContext();
        String uri = getURI(clientConfigBuilderContext.getHost(), clientConfigBuilderContext.getPort(),
                            processorContext.getRequestContext());
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
        processorContext.setRequest(request);
        populateHeader(processorContext);
        populateCookies(processorContext);
        populateQueryParameters(processorContext);
    }

    private void populateHeader(HttpClientProcessorContext processorContext) {
        HttpRequest request = processorContext.getRequest();
        HttpClientRequestBuilderContext requestContext = processorContext.getRequestContext();
        request.headers().set(HttpHeaders.Names.HOST, processorContext.getClientInformationContext()
                .getClientConfigBuilderContext().getHost());
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
        request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);
        if (requestContext.getBody() != null) {
            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, requestContext.getBody().getBytes()
                    .length);
        }
        if (requestContext.getHeaders() != null) {
            for (Header header : requestContext.getHeaders()) {
                request.headers().set(header.getName(), header.getValue());
            }
        }
    }

    private void populateCookies(HttpClientProcessorContext processorContext) {
        HttpClientRequestBuilderContext requestContext = processorContext.getRequestContext();
        if (requestContext.getCookies() != null) {
            DefaultCookie[] cookies = new DefaultCookie[requestContext.getCookies().size()];
            int i = 0;
            for (Cookie cookie : requestContext.getCookies()) {
                cookies[i++] = new DefaultCookie(cookie.getName(), cookie.getValue());
            }
            processorContext.getRequest().headers().set(
                    HttpHeaders.Names.COOKIE,
                    ClientCookieEncoder.STRICT.encode(cookies));
        }
    }

    private void populateQueryParameters(HttpClientProcessorContext processorContext) {

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
