package org.wso2.gw.emulator.http.client.contexts;

import io.netty.handler.codec.http.HttpRequest;

public class HttpClientRequestProcessorContext extends HttpClientProcessorContext {

    private HttpClientRequestBuilderContext requestBuilderContext;
    private HttpRequest request;

    public HttpClientRequestBuilderContext getRequestBuilderContext() {
        return requestBuilderContext;
    }

    public void setRequestBuilderContext(HttpClientRequestBuilderContext requestBuilderContext) {
        this.requestBuilderContext = requestBuilderContext;
    }

    public HttpRequest getRequest() {
        return request;
    }



    public void setRequest(HttpRequest request) {
        this.request = request;
    }
}
