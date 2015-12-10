package org.wso2.gw.emulator.http.client;
import org.wso2.gw.emulator.http.server.HttpServerRequestBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerResponseBuilderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/8/15.
 */
public class HttpClientInformationContext {
    private HttpClientConfigBuilderContext clientConfigBuilderContext;
    private Map<HttpClientRequestBuilderContext,HttpClientResponseBuilderContext> correlation;
    private HttpClientRequestBuilderContext httpClientRequestBuilderContext;
    private HttpClientResponseBuilderContext httpClientResponseBuilderContext;

    public HttpClientConfigBuilderContext getClientConfigBuilderContext() {
        return clientConfigBuilderContext;
    }

    public void setClientConfigBuilderContext(HttpClientConfigBuilderContext clientConfigBuilderContext) {
        this.clientConfigBuilderContext = clientConfigBuilderContext;
    }

    public Map<HttpClientRequestBuilderContext, HttpClientResponseBuilderContext> getCorrelation() {
        return correlation;
    }

    public void addCorrelation(HttpClientRequestBuilderContext httpClientRequestBuilderContext, HttpClientResponseBuilderContext httpClientResponseBuilderContext){
        if(correlation == null){
            this.correlation = new HashMap<HttpClientRequestBuilderContext, HttpClientResponseBuilderContext>();
        }
        correlation.put(httpClientRequestBuilderContext,httpClientResponseBuilderContext);
    }
}
