package org.wso2.gw.emulator.http.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/8/15.
 */
public class HttpServerInformationContext {
    private HttpServerConfigBuilderContext serverConfigBuilderContext;
    private Map<HttpServerRequestBuilderContext,HttpServerResponseBuilderContext> correlation;

    public HttpServerConfigBuilderContext getServerConfigBuilderContext() {
        return serverConfigBuilderContext;
    }

    public void setServerConfigBuilderContext(HttpServerConfigBuilderContext serverConfigBuilderContext) {
        this.serverConfigBuilderContext = serverConfigBuilderContext;
    }

    public Map<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext> getCorrelation() {
        return correlation;
    }

    public void addCorrelation(HttpServerRequestBuilderContext httpServerRequestBuilderContext,HttpServerResponseBuilderContext httpServerResponseBuilderContext){
        if(correlation == null){
            this.correlation = new HashMap<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext>();
        }
        correlation.put(httpServerRequestBuilderContext,httpServerResponseBuilderContext);
    }








}