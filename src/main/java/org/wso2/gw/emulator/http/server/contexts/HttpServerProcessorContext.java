package org.wso2.gw.emulator.http.server.contexts;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;

public class HttpServerProcessorContext {

    private HttpServerInformationContext serverInformationContext;
    private HttpRequestContext httpRequestContext;
    private HttpRequest httpRequest;
    private HttpContent httpContent;
    private HttpServerResponseBuilderContext selectedResponseContext;
    private FullHttpResponse finalResponse;

    public HttpServerInformationContext getServerInformationContext() {
        return serverInformationContext;
    }

    public void setServerInformationContext(HttpServerInformationContext serverInformationContext) {
        this.serverInformationContext = serverInformationContext;
    }

    public HttpRequestContext getHttpRequestContext() {
        return httpRequestContext;
    }

    public void setHttpRequestContext(HttpRequestContext httpRequestContext) {
        this.httpRequestContext = httpRequestContext;
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpContent getHttpContent() {
        return httpContent;
    }

    public void setHttpContent(HttpContent httpContent) {
        this.httpContent = httpContent;
    }

    public HttpServerResponseBuilderContext getSelectedResponseContext() {
        return selectedResponseContext;
    }

    public void setSelectedResponseContext(HttpServerResponseBuilderContext selectedResponseContext) {
        this.selectedResponseContext = selectedResponseContext;
    }

    public FullHttpResponse getFinalResponse() {
        return finalResponse;
    }

    public void setFinalResponse(FullHttpResponse finalResponse) {
        this.finalResponse = finalResponse;
    }
}
