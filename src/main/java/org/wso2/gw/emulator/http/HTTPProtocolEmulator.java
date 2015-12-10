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
import org.wso2.gw.emulator.core.EmulatorType;
import org.wso2.gw.emulator.http.client.HttpClientGivenBuilderContext;
import org.wso2.gw.emulator.http.client.HttpClientInformationContext;
import org.wso2.gw.emulator.http.server.HttpServerGivenBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerInformationContext;

public class HTTPProtocolEmulator extends AbstractProtocolEmulator {

    private HttpServerInformationContext serverInformatioContext;
    private HttpServerGivenBuilderContext serverGivenBuilderContext;
    private HttpClientInformationContext clientInformatioContext;
    private HttpClientGivenBuilderContext clientGivenBuilderContext;
    /*private HttpEmulatorConsumerInitializer httpEmulatorConsumerInitializer;
    private HttpEmulatorProducerInitializer httpEmulatorProducerInitializer;
    private HttpServerBuilde consumerContext;
    private HttpClientBuilder httpProducerContext;*/

    public HTTPProtocolEmulator(Emulator emulator) {
        super(emulator);
    }

    @Override
    public HttpServerGivenBuilderContext server() {
        /*consumerContext = new HttpServerBuilde(this);
        setEmulatorType(EmulatorType.HTTP_CONSUMER);
        httpEmulatorConsumerInitializer = new HttpEmulatorConsumerInitializer(consumerContext);
        return consumerContext;*/
        serverInformatioContext = new HttpServerInformationContext();
        serverGivenBuilderContext = new HttpServerGivenBuilderContext(serverInformatioContext);
        return serverGivenBuilderContext;

    }

    @Override
    public HttpClientGivenBuilderContext client() {
        /*httpProducerContext = new HttpClientBuilder(this);
        setEmulatorType(EmulatorType.HTTP_PRODUCER);
        this.httpEmulatorProducerInitializer = new HttpEmulatorProducerInitializer(httpProducerContext);
        return httpProducerContext;*/
        clientInformatioContext = new HttpClientInformationContext();
        clientGivenBuilderContext = new HttpClientGivenBuilderContext(clientInformatioContext);
        return clientGivenBuilderContext;
    }

    /*public HttpEmulatorConsumerInitializer getHttpEmulatorConsumerInitializer() {
        return httpEmulatorConsumerInitializer;
    }

    public HttpEmulatorProducerInitializer getHttpEmulatorProducerInitializer() {
        return httpEmulatorProducerInitializer;
    }

    public HttpServerBuilde getConsumerContext() {
        return consumerContext;
    }

    public HttpClientBuilder getHttpProducerContext() {
        return httpProducerContext;
    }
*/


}
