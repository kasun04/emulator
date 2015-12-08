package org.wso2.gw.emulator.http.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chamile on 12/8/15.
 */
public class HttpServerInformationContext {
    private HttpServerConfigBuilderContext configBuilderContext;
    private Map<HttpServerRequestBuilderContext,HttpServerResponseBuilderContext> correlation;

    public HttpServerConfigBuilderContext getConfigBuilderContext() {
        return configBuilderContext;
    }

    public void setConfigBuilderContext(HttpServerConfigBuilderContext configBuilderContext) {
        this.configBuilderContext = configBuilderContext;
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
