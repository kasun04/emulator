package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.http.client.HttpClientInitializer;
import org.wso2.gw.emulator.http.server.contexts.HttpServerConfigBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerRequestBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerResponseBuilderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/8/15.
 */
public class HttpClientInformationContext {
    private HttpClientConfigBuilderContext clientConfigBuilderContext;
    private Map<HttpClientRequestBuilderContext, HttpClientResponseBuilderContext> correlation;
    private HttpClientInitializer clientInitializer;
    private HttpClientRequestBuilderContext requestContext;
    private HttpClientResponseBuilderContext expectedResponse;

    public HttpClientConfigBuilderContext getClientConfigBuilderContext() {
        return clientConfigBuilderContext;
    }

    public void setClientConfigBuilderContext(HttpClientConfigBuilderContext clientConfigBuilderContext) {
        this.clientConfigBuilderContext = clientConfigBuilderContext;
    }

    public Map<HttpClientRequestBuilderContext, HttpClientResponseBuilderContext> getRequestResponseCorrelation() {
        return correlation;
    }

    public void addCorrelation(HttpClientRequestBuilderContext httpClientRequestBuilderContext,
                               HttpClientResponseBuilderContext httpClientResponseBuilderContext){
        if(correlation == null){
            this.correlation = new HashMap<HttpClientRequestBuilderContext, HttpClientResponseBuilderContext>();
        }
        correlation.put(httpClientRequestBuilderContext, httpClientResponseBuilderContext);
    }

    public HttpClientInitializer getClientInitializer() {
        return clientInitializer;
    }

    public void setClientInitializer(HttpClientInitializer clientInitializer) {
        this.clientInitializer = clientInitializer;
    }

    public HttpClientRequestBuilderContext getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(HttpClientRequestBuilderContext requestContext) {
        this.requestContext = requestContext;
    }

    public HttpClientResponseBuilderContext getExpectedResponse() {
        return expectedResponse;
    }

    public void setExpectedResponse(HttpClientResponseBuilderContext expectedResponse) {
        this.expectedResponse = expectedResponse;
    }
}
