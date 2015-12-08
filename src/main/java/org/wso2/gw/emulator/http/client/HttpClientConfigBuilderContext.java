package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.AbstractProtocolEmulator;
import org.wso2.gw.emulator.http.HTTPProtocolEmulator;
import org.wso2.gw.emulator.http.dsl.producer.IncomingMessage;
import org.wso2.gw.emulator.http.dsl.producer.OutgoingMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientConfigBuilderContext extends AbstractConfigurationBuilderContext {
    private IncomingMessage incoming;
    private Map<IncomingMessage, OutgoingMessage> inOutCorrelation = new HashMap<IncomingMessage, OutgoingMessage>();
    private HTTPProtocolEmulator httpProtocolEmulator;


    public HttpClientConfigBuilderContext(HTTPProtocolEmulator httpProtocolEmulator) {
        this.httpProtocolEmulator = httpProtocolEmulator;
    }

    @Override
    public void host() {

    }

    @Override
    public void port() {

    }


    public HttpClientConfigBuilderContext when(IncomingMessage incoming) {
        this.incoming = incoming;
        return this;
    }

    public HttpClientConfigBuilderContext respond(OutgoingMessage outgoing) {
        this.inOutCorrelation.put(incoming, outgoing);
        return this;
    }

    public AbstractProtocolEmulator operations() {
        return httpProtocolEmulator;
    }

    public IncomingMessage getIncoming() {
        return incoming;
    }

    public Map<IncomingMessage, OutgoingMessage> getInOutCorrelation() {
        return inOutCorrelation;
    }

}
