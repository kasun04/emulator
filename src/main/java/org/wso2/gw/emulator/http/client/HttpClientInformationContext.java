package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.http.server.HttpServerConfigBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerRequestBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerResponseBuilderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/8/15.
 */
public class HttpClientInformationContext {
    private HttpServerConfigBuilderContext clientConfigBuilderContext;
    private Map<HttpServerRequestBuilderContext,HttpServerResponseBuilderContext> correlation;

    public HttpServerConfigBuilderContext getClientConfigBuilderContext() {
        return clientConfigBuilderContext;
    }

    public void setClientConfigBuilderContext(HttpServerConfigBuilderContext clientConfigBuilderContext) {
        this.clientConfigBuilderContext = clientConfigBuilderContext;
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
