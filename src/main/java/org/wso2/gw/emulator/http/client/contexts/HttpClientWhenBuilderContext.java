package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractWhenBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientOperationBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientRequsetBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerInformationContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerOperationBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerThenBuilderContext;

import java.util.List;

public class HttpClientWhenBuilderContext extends AbstractWhenBuilderContext<HttpClientRequestBuilderContext> {

    private final HttpClientInformationContext httpInformationContext;
    private final List<HttpClientWhenBuilderContext> whenBuilderContextList;
    private HttpClientRequestBuilderContext requestContext;
    private HttpClientInformationContext httpClientInformationContext;
    private HttpClientThenBuilderContext thenBuilderContext;
    private HttpClientOperationBuilderContext httpClientOperationBuilderContext;

    public HttpClientWhenBuilderContext(List<HttpClientWhenBuilderContext> whenBuilderContextList, HttpClientInformationContext httpClientInformationContext){
        this.httpInformationContext =httpClientInformationContext;
        this.whenBuilderContextList = whenBuilderContextList;
        this.whenBuilderContextList.add(this);
    }

    @Override
    public HttpClientThenBuilderContext when(HttpClientRequestBuilderContext requestContext) {
        this.requestContext = requestContext;
        this.requestContext.buildPathRegex(httpClientInformationContext.getClientConfigBuilderContext().getContext());
        thenBuilderContext = new HttpClientThenBuilderContext(whenBuilderContextList,requestContext,httpClientInformationContext);
        return thenBuilderContext;
    }

    @Override
    public HttpClientOperationBuilderContext operation() {
        this.httpClientOperationBuilderContext = new HttpClientOperationBuilderContext(httpClientInformationContext);
        return httpClientOperationBuilderContext;
    }
}