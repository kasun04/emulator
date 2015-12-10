package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.http.HTTPProtocolEmulator;
import org.wso2.gw.emulator.http.dsl.producer.IncomingMessage;
import org.wso2.gw.emulator.http.dsl.producer.OutgoingMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientConfigBuilderContext extends AbstractConfigurationBuilderContext {

    private static HttpClientConfigBuilderContext clientConfigBuilderContext;
    private Map<IncomingMessage, OutgoingMessage> inOutCorrelation = new HashMap<IncomingMessage, OutgoingMessage>();
    private String host;
    private int port;

    private static HttpClientConfigBuilderContext getInstance() {
        clientConfigBuilderContext = new HttpClientConfigBuilderContext();
        return clientConfigBuilderContext;
    }

    public static HttpClientConfigBuilderContext configure() {
        return getInstance();
    }

    public HttpClientConfigBuilderContext host(String host) {
        this.host=host;
        return this;
    }

    public HttpClientConfigBuilderContext port(int port) {
        this.port=port;
        return this;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
