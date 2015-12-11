package org.wso2.gw.emulator.http.client.contexts;

import io.netty.handler.codec.http.HttpResponse;

public class HttpClientProcessorContext {

    private HttpClientInformationContext clientInformationContext;


    public HttpClientInformationContext getClientInformationContext() {
        return clientInformationContext;
    }

    public void setClientInformationContext(HttpClientInformationContext clientInformationContext) {
        this.clientInformationContext = clientInformationContext;
    }
}
