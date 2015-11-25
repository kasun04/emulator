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

import org.apache.log4j.Logger;
import org.wso2.gw.emulator.sampletcp.dsl.dao.TcpConsumerContext;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpEmulatorConsumerInitializer {
    private static final Logger log = Logger.getLogger(TcpEmulatorConsumerInitializer.class);
    private TcpConsumerContext consumerContext;
    private ServerSocket listener;
    private Socket socket;

    public TcpEmulatorConsumerInitializer(TcpConsumerContext consumerContext) {
        this.consumerContext = consumerContext;
    }

    public void initialize() throws Exception {
        DataOutputStream out;
        BufferedReader in;

        listener = new ServerSocket(consumerContext.getPort(), 5);
        try {
            while (true) {
                socket = listener.accept();
                try {
                    out = new DataOutputStream(socket.getOutputStream());
                    out.flush();
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String requestContent = getRequest(in);
                    TcpRequestContext requestContext = new TcpRequestContext();
                    requestContext.setRequestBody(requestContent);
                    new TcpResponseProcessor().process(out, consumerContext, requestContext);
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }

    public void shutdown() {
        try {
            listener.close();
            socket.close();
        } catch (Exception e) {
            log.error("Exception occurred while shutting down the server", e);
        }

    }

    public String getRequest(BufferedReader in) throws Exception {
        StringBuffer requestContent = new StringBuffer();
        String content;
        while ((content = in.readLine()) != null) {
            if (content.equals("QUIT")) {
                break;
            }
            requestContent.append(content);
        }
        return requestContent.toString();
    }
}
