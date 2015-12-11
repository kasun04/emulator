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

import org.wso2.gw.emulator.http.client.contexts.HttpClientProcessorContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientResponseProcessorContext;

public class HttpResponseAssertProcessor extends AbstractClientProcessor<HttpClientResponseProcessorContext> {

    @Override
    public void process(HttpClientResponseProcessorContext processorContext) {
        assertResponseContent(processorContext);
        assertHeaderParameters(processorContext);
    }

    private void assertResponseContent(HttpClientResponseProcessorContext processorContext) {
        if (processorContext.getExpectedResponse().getBody().equalsIgnoreCase(processorContext.getReceivedResponseContext()
                                                                                      .getResponseBody())) {
            System.out.print("Equal");
        } else {
            System.out.print("Wrong");
        }
    }

    private void assertHeaderParameters(HttpClientProcessorContext processorContext) {

    }
}
