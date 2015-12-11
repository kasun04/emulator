package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractClientOperationBuilderContext;

public class HttpClientOperationBuilderContext extends AbstractClientOperationBuilderContext {

    private HttpClientInformationContext httpClientInformationContext;

    public HttpClientOperationBuilderContext(HttpClientInformationContext httpClientInformationContext) {
        this.httpClientInformationContext = httpClientInformationContext;
    }

    @Override
    public void send() {
        try {
            this.httpClientInformationContext.getClientInitializer().initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
