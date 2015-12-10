package org.wso2.gw.emulator.http.client.contexts;


public class HttpClientProcessorContext {

    private HttpClientInformationContext clientInformationContext;
    private HttpClientRequestBuilderContext request;
    private HttpClientResponseBuilderContext expectedResponse;


    public HttpClientInformationContext getClientInformationContext() {
        return clientInformationContext;
    }

    public void setClientInformationContext(HttpClientInformationContext clientInformationContext) {
        this.clientInformationContext = clientInformationContext;
    }

    public HttpClientRequestBuilderContext getRequest() {
        return request;
    }

    public void setRequest(HttpClientRequestBuilderContext request) {
        this.request = request;
    }

    public HttpClientResponseBuilderContext getExpectedResponse() {
        return expectedResponse;
    }

    public void setExpectedResponse(HttpClientResponseBuilderContext expectedResponse) {
        this.expectedResponse = expectedResponse;
    }
}
