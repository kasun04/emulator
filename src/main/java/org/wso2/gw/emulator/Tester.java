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

package org.wso2.gw.emulator;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.wso2.gw.emulator.core.Emulator;

import static org.wso2.gw.emulator.http.dsl.dto.IncomingMessage.request;
import static org.wso2.gw.emulator.http.dsl.dto.OutgoingMessage.response;

public class Tester {
    public static void main(String[] args) {
        Emulator.getHttpEmulator()
                .consumer()
                .host("127.0.0.1")
                .port(6065)
                .writingDelay(4000)
                .context("/user")
                .when(request().withMethod(HttpMethod.POST).withBody("TestRequest").withHeader("header1",
                                                                                               "headerValue").
                        withQueryParameter("test", "123"))
                .respond(response().withBody("Test Response1").withStatusCode(HttpResponseStatus.OK))
                .when(request().withPath("/name"))
                .respond(response().withBody("Test response2").withStatusCode(HttpResponseStatus.FORBIDDEN))
                .operations().start();

    }
}
