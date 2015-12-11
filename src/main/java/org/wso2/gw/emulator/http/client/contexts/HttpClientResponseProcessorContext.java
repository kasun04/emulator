package org.wso2.gw.emulator.http.client.contexts;

import io.netty.handler.codec.http.HttpResponse;

public class HttpClientResponseProcessorContext extends HttpClientProcessorContext {

    private HttpClientResponseBuilderContext expectedResponse;
    private HttpResponseContext receivedResponseContext;
    private HttpResponse receivedResponse;

    public HttpClientResponseBuilderContext getExpectedResponse() {
        return expectedResponse;
    }

    public void setExpectedResponse(HttpClientResponseBuilderContext expectedResponse) {
        this.expectedResponse = expectedResponse;
    }

    public HttpResponseContext getReceivedResponseContext() {
        return receivedResponseContext;
    }

    public void setReceivedResponseContext(HttpResponseContext receivedResponseContext) {
        this.receivedResponseContext = receivedResponseContext;
    }

    public HttpResponse getReceivedResponse() {
        return receivedResponse;
    }

    public void setReceivedResponse(HttpResponse receivedResponse) {
        this.receivedResponse = receivedResponse;
    }

}
