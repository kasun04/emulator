package org.wso2.gw.emulator.http.server.contexts;

import org.wso2.gw.emulator.http.server.HttpServerInitializer;
import org.wso2.gw.emulator.http.server.contexts.HttpServerRequestBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerResponseBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerConfigBuilderContext;

import java.util.HashMap;
import java.util.Map;

public class HttpServerInformationContext {
    private HttpServerConfigBuilderContext serverConfigBuilderContext;
    private Map<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext> correlation;
    private HttpServerInitializer httpServerInitializer;

    public HttpServerConfigBuilderContext getServerConfigBuilderContext() {
        return serverConfigBuilderContext;
    }

    public void setServerConfigBuilderContext(HttpServerConfigBuilderContext serverConfigBuilderContext) {
        this.serverConfigBuilderContext = serverConfigBuilderContext;
    }

    public Map<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext> getRequestResponseCorrelation() {
        return correlation;
    }

    public void addCorrelation(HttpServerRequestBuilderContext httpServerRequestBuilderContext, HttpServerResponseBuilderContext httpServerResponseBuilderContext) {
        if (correlation == null) {
            this.correlation = new HashMap<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext>();
        }
        correlation.put(httpServerRequestBuilderContext, httpServerResponseBuilderContext);
    }

    public HttpServerInitializer getHttpServerInitializer() {
        return httpServerInitializer;
    }

    public void setHttpServerInitializer(HttpServerInitializer httpServerInitializer) {
        this.httpServerInitializer = httpServerInitializer;
    }
}
