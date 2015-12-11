package org.wso2.gw.emulator.http.client.contexts;


import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

public class HttpClientProcessorContext {

    private HttpClientInformationContext clientInformationContext;
    private HttpClientRequestBuilderContext requestContext;
    private HttpClientResponseBuilderContext expectedResponse;
    private HttpResponseContext receivedResponse;
    private HttpRequest request;
    private HttpResponse httpResponse;


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

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public HttpResponseContext getReceivedResponse() {
        return receivedResponse;
    }

    public void setReceivedResponse(HttpResponseContext receivedResponse) {
        this.receivedResponse = receivedResponse;
    }
}
