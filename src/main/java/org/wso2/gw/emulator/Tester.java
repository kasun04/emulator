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
import org.wso2.gw.emulator.http.client.contexts.HttpClientConfigBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientRequestBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientResponseBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientResponseProcessorContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerOperationBuilderContext;

import java.io.FileNotFoundException;

import static org.wso2.gw.emulator.http.server.contexts.HttpServerRequestBuilderContext.request;
import static org.wso2.gw.emulator.http.server.contexts.HttpServerResponseBuilderContext.response;
import static org.wso2.gw.emulator.http.server.contexts.HttpServerConfigBuilderContext.configure;

public class Tester {
    public static void main(String[] args) throws Exception {
        HttpServerOperationBuilderContext serverOperationBuilderContext = startHttpEmulator();
        Thread.sleep(1000);
        testProducer();
        //testProducer1();
        serverOperationBuilderContext.stop();
    }

    private static HttpServerOperationBuilderContext startHttpEmulator() throws FileNotFoundException{
        return Emulator.getHttpEmulator()
                .server()
                .given(configure()

                        .host("127.0.0.1")
                        .port(6065)
                        .context("/user").readingDelay(1000).writingDelay(1000)
                                .randomConnectionClose(false).logicDelay(1000))

                .when(request()
                        .withMethod(HttpMethod.GET).withPath("*")
                )
                .then(response()
                        .withBody("Test Response1").withStatusCode(HttpResponseStatus.OK))
                .when(request()
                        .withMethod(HttpMethod.POST).withBody("test")
                )
                .then(response()
                        .withBody("Test Response2").withStatusCode(HttpResponseStatus.OK))
                /*.when(request()
                        .withMethod(HttpMethod.POST).withBody("files/ServerRequest.txt")
                )
                .then(response()
                        .withBody("files/ServerResponse.txt").withStatusCode(HttpResponseStatus.OK))*//*
                */.when(request()
                              .withMethod(HttpMethod.POST).withPath("/dilshan")
                              .withBody("test")
                              .withHeader("name2","value2")
                        )
                .then(response()
                              .withBody("response")
                              .withHeader("name3","value3")

                              .withStatusCode(HttpResponseStatus.OK)

                )
                .operation().start();
    }

    private static HttpClientResponseProcessorContext testProducer() {
        return Emulator.getHttpEmulator()
                .client()
                .given(HttpClientConfigBuilderContext.configure()
                                .host("127.0.0.1").port(6065))
                .when(HttpClientRequestBuilderContext.request()
                              .withPath("/user").withMethod(HttpMethod.GET))
                .then(HttpClientResponseBuilderContext.response().withBody("Test Response1"))
                /*.when(HttpClientRequestBuilderContext.request()
                        .withMethod(HttpMethod.POST).withBody("files/ClientRequest.txt"))
                .then(HttpClientResponseBuilderContext.response().withBody("files/ClientResponse.txt"))*/
                .when(HttpClientRequestBuilderContext.request()
                              .withPath("/user").withMethod(HttpMethod.POST).withBody("test"))
                .then(HttpClientResponseBuilderContext.response().withBody("Test Response2").assertionIgnore())
                .operation().send();
    }

    private static HttpClientResponseProcessorContext testProducer1() throws FileNotFoundException {
        return Emulator.getHttpEmulator()
                .client()
                .given(HttpClientConfigBuilderContext.configure()
                        .host("127.0.0.1")
                        .port(6065)
                        .readingDelay(1000)
                )
                .when(HttpClientRequestBuilderContext.request()
                        .withMethod(HttpMethod.POST).withPath("/user/dilshan")
                        .withBody("test")
                        .withHeader("name2","value2")//.withHeaders()
                              //.withQueryParameter("q1","q1")
                )
                .then(HttpClientResponseBuilderContext.response()
                        .withBody("response")
                        .withHeader("name3","value3")
                )
                .operation().send();
    }
}
