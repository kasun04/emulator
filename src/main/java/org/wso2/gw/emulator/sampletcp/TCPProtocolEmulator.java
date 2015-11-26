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
package org.wso2.gw.emulator.sampletcp;

import org.wso2.gw.emulator.core.AbstractProtocolEmulator;
import org.wso2.gw.emulator.core.Emulator;
import org.wso2.gw.emulator.core.EmulatorType;
import org.wso2.gw.emulator.sampletcp.dsl.dao.TcpConsumerContext;
import org.wso2.gw.emulator.sampletcp.dsl.dao.TcpProducerContext;

public class TCPProtocolEmulator extends AbstractProtocolEmulator {
    private TcpEmulatorConsumerInitializer tcpEmulatorInitializer;
    private TcpConsumerContext tcpConsumerContext;

    public TCPProtocolEmulator(Emulator emulator) {
        super(emulator);
    }

    @Override
    public TcpConsumerContext consumer() {
        tcpConsumerContext = new TcpConsumerContext(this);
        setEmulatorType(EmulatorType.TCP_CONSUMER);
        tcpEmulatorInitializer = new TcpEmulatorConsumerInitializer(tcpConsumerContext);
        return tcpConsumerContext;
    }

    @Override
    public TcpProducerContext producer() {
        TcpProducerContext tcpProducerContext = new TcpProducerContext();
        setEmulatorType(EmulatorType.TCP_PRODUCER);
        return tcpProducerContext;
    }

    public TcpEmulatorConsumerInitializer getEmulatorInitializer() {
        return tcpEmulatorInitializer;
    }

    public TcpConsumerContext getTcpConsumerContext() {
        return tcpConsumerContext;
    }
}
