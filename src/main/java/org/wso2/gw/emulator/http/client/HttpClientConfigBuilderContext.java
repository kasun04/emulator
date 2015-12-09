package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.AbstractProtocolEmulator;
import org.wso2.gw.emulator.http.HTTPProtocolEmulator;
import org.wso2.gw.emulator.http.dsl.producer.HttpClientBuilderContext;
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
    private HTTPProtocolEmulator httpProtocolEmulator;
    private String host;
    private int port;

    /*public HttpClientConfigBuilderContext(HTTPProtocolEmulator httpProtocolEmulator) {
        this.httpProtocolEmulator = httpProtocolEmulator;
    }
*/
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
    public HttpClientConfigBuilderContext when(IncomingMessage incoming) {
        this.incoming = incoming;
        return this;
    }

    public HttpClientConfigBuilderContext respond(OutgoingMessage outgoing) {
        this.inOutCorrelation.put(incoming, outgoing);
        return this;
    }

    /*public AbstractProtocolEmulator operations() {
        return httpProtocolEmulator;
    }

    public IncomingMessage getIncoming() {
        return incoming;
    }

    public Map<IncomingMessage, OutgoingMessage> getInOutCorrelation() {
        return inOutCorrelation;
    }
*/
}
