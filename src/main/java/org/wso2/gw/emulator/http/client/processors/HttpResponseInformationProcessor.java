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

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.CharsetUtil;
import org.wso2.gw.emulator.http.client.contexts.HttpClientProcessorContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientResponseBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientResponseProcessorContext;
import org.wso2.gw.emulator.http.client.contexts.HttpResponseContext;

import java.util.Map;

public class HttpResponseInformationProcessor extends AbstractClientProcessor<HttpClientResponseProcessorContext> {

    @Override
    public void process(HttpClientResponseProcessorContext processorContext) {
        HttpResponseContext httpResponseContext = new HttpResponseContext();
        processorContext.setReceivedResponseContext(httpResponseContext);
        processorContext.setReceivedResponseContext(httpResponseContext);
        processorContext.setExpectedResponse(processorContext.getClientInformationContext().getExpectedResponse());
        populateRequestHeaders(processorContext);
    }

    private void populateRequestHeaders(HttpClientResponseProcessorContext processorContext) {
        HttpHeaders headers = processorContext.getReceivedResponse().headers();
        if (!headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entries()) {
                processorContext.getReceivedResponseContext().addHeaderParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    public void appendDecoderResult(HttpResponseContext responseContext, HttpObject httpObject, ByteBuf content) {
        responseContext.appendResponseContent(content.toString(CharsetUtil.UTF_8));
        DecoderResult result = httpObject.getDecoderResult();
        if (result.isSuccess()) {
            return;
        }
        responseContext.appendResponseContent(result.cause());
    }
}
