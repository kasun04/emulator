package org.wso2.gw.emulator.http.server.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractWhenBuilderContext;

import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerWhenBuilderContext extends AbstractWhenBuilderContext<HttpServerRequestBuilderContext>{
    private final HttpServerInformationContext httpServerInformationContext;
    private HttpServerThenBuilderContext thenBuilderContext;
    private List<HttpServerWhenBuilderContext> whenBuilderContextList;
    private HttpServerRequestBuilderContext requestContext;

    public HttpServerWhenBuilderContext(List<HttpServerWhenBuilderContext> whenBuilderContextList,HttpServerInformationContext httpServerInformationContext){
        this.httpServerInformationContext =httpServerInformationContext;
        this.whenBuilderContextList = whenBuilderContextList;
        this.whenBuilderContextList.add(this);
    }

    @Override
    public HttpServerThenBuilderContext when(HttpServerRequestBuilderContext requestContext) {
        this.requestContext = requestContext;
        thenBuilderContext = new HttpServerThenBuilderContext(whenBuilderContextList,requestContext,httpServerInformationContext);
        return thenBuilderContext;
    }

    @Override
    public HttpServerOperationBuilderContext operation() {
        System.out.println("debug point 1");
        // return httpProtocolEmulator;
        return null;
    }

    public HttpServerRequestBuilderContext getRequestContext() {
        return requestContext;
    }

}
