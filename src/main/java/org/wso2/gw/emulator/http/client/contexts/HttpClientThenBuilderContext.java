package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractResponseBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractThenBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerInformationContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerRequestBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerResponseBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerWhenBuilderContext;

import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientThenBuilderContext extends AbstractThenBuilderContext<HttpClientResponseBuilderContext>{

    private final HttpClientRequestBuilderContext requestContext;
    private final HttpClientInformationContext httpClientInformationContext;
    private final List<HttpClientWhenBuilderContext> whenBuilderContextList;
    private HttpClientResponseBuilderContext responseContext;
    private HttpClientWhenBuilderContext whenBuilderContext;

    public HttpClientThenBuilderContext(List<HttpClientWhenBuilderContext> whenBuilderContextList, HttpClientRequestBuilderContext requestContext, HttpClientInformationContext httpClientInformationContext) {
        this.requestContext = requestContext;
        this.httpClientInformationContext = httpClientInformationContext;
        this.whenBuilderContextList = whenBuilderContextList;
    }
    @Override
    public HttpClientWhenBuilderContext then(HttpClientResponseBuilderContext responseContext) {
        this.responseContext = responseContext;
        whenBuilderContext = new HttpClientWhenBuilderContext(whenBuilderContextList,httpClientInformationContext);
        this.httpClientInformationContext.addCorrelation(requestContext, responseContext);
        return whenBuilderContext;
    }
}
