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

import org.wso2.gw.emulator.core.AbstractProtocolEmulator;
import org.wso2.gw.emulator.core.Emulator;
import org.wso2.gw.emulator.http.server.HttpEmulatorConsumerInitializer;
import org.wso2.gw.emulator.http.server.HttpServerGivenBuilderContext;
import org.wso2.gw.emulator.http.dsl.producer.HttpClientBuilderContext;
import org.wso2.gw.emulator.http.client.HttpClientGivenBuilderContext;
import org.wso2.gw.emulator.http.client.HttpEmulatorProducerInitializer;

public class HTTPProtocolEmulator extends AbstractProtocolEmulator {

    private HttpEmulatorConsumerInitializer httpEmulatorConsumerInitializer;
    private HttpEmulatorProducerInitializer httpEmulatorProducerInitializer;
    private HttpServerGivenBuilderContext serverContext;
    private HttpClientBuilderContext clientContext;

    public HTTPProtocolEmulator(Emulator emulator) {
        super(emulator);
    }

    @Override
    public HttpServerGivenBuilderContext server() {
        /*serverContext = new HttpServerGivenBuilderContext();
        setEmulatorType(EmulatorType.HTTP_CONSUMER);
        httpEmulatorConsumerInitializer = new HttpEmulatorConsumerInitializer(serverContext);
        return serverContext;*/
        return null;

    }

    @Override
    public HttpClientGivenBuilderContext client() {
        /*clientContext = new HttpClientBuilderContext(this);
        setEmulatorType(EmulatorType.HTTP_PRODUCER);
        this.httpEmulatorProducerInitializer = new HttpEmulatorProducerInitializer(clientContext);
        return clientContext;*/
        return null;
    }

    public HttpEmulatorConsumerInitializer getHttpEmulatorConsumerInitializer() {
        return httpEmulatorConsumerInitializer;
    }

    public HttpEmulatorProducerInitializer getHttpEmulatorProducerInitializer() {
        return httpEmulatorProducerInitializer;
    }

    public HttpServerGivenBuilderContext getServerContext() {
        return serverContext;
    }

    public HttpClientBuilderContext getClientContext() {
        return clientContext;
    }
}
