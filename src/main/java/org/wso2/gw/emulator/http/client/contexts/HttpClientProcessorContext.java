package org.wso2.gw.emulator.http.client.contexts;


import io.netty.handler.codec.http.HttpRequest;

public class HttpClientProcessorContext {

    private HttpClientInformationContext clientInformationContext;
    private HttpClientRequestBuilderContext requestContext;
    private HttpClientResponseBuilderContext expectedResponse;
    private HttpRequest request;


    public HttpClientInformationContext getClientInformationContext() {
        return clientInformationContext;
    }

    public void setClientInformationContext(HttpClientInformationContext clientInformationContext) {
        this.clientInformationContext = clientInformationContext;
    }

    public HttpClientRequestBuilderContext getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(HttpClientRequestBuilderContext requestContext) {
        this.requestContext = requestContext;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public HttpClientResponseBuilderContext getExpectedResponse() {
        return expectedResponse;
    }

    public void setExpectedResponse(HttpClientResponseBuilderContext expectedResponse) {
        this.expectedResponse = expectedResponse;
    }
}
