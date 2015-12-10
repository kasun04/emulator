package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractResponseBuilderContext;
import org.wso2.gw.emulator.core.AbstractThenBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerInformationContext;
import org.wso2.gw.emulator.http.server.HttpServerRequestBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerResponseBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerWhenBuilderContext;

import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientThenBuilderContext extends AbstractThenBuilderContext<HttpClientResponseBuilderContext>{
    private final HttpClientRequestBuilderContext requestContext;
    private final HttpClientInformationContext httpClientInformationContext;
    private HttpClientWhenBuilderContext whenBuilderContext;
    private List<HttpClientWhenBuilderContext> whenBuilderContextList;
    private HttpClientResponseBuilderContext responseContext;

    public HttpClientThenBuilderContext(List<HttpClientWhenBuilderContext> whenBuilderContextList, HttpClientRequestBuilderContext requestContext, HttpClientInformationContext httpClientInformationContext){
        this.requestContext = requestContext;
        this.httpClientInformationContext = httpClientInformationContext;
        this.whenBuilderContextList = whenBuilderContextList;
    }

    @Override
    public HttpClientWhenBuilderContext then(HttpClientResponseBuilderContext responseContext) {
        this.responseContext = responseContext;
        whenBuilderContext = new HttpClientWhenBuilderContext(whenBuilderContextList,httpClientInformationContext);
        this.httpClientInformationContext.addCorrelation(requestContext,responseContext);
        return whenBuilderContext;
    }
}
