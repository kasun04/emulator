package org.wso2.gw.emulator.http.server.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractServerOperationBuilderContext;

public class HttpServerOperationBuilderContext extends AbstractServerOperationBuilderContext {
    private HttpServerInformationContext httpServerInformationContext;

    public HttpServerOperationBuilderContext(HttpServerInformationContext httpServerInformationContext) {
        this.httpServerInformationContext = httpServerInformationContext;
    }

    @Override
    public HttpServerOperationBuilderContext start() {
        try {
            httpServerInformationContext.getHttpServerInitializer().initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public HttpServerOperationBuilderContext stop() {
        try {
            httpServerInformationContext.getHttpServerInitializer().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
