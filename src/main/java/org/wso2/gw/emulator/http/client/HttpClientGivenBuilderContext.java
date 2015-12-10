package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.AbstractGivenBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerGivenBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerInformationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientGivenBuilderContext extends AbstractGivenBuilderContext<HttpClientConfigBuilderContext>{
    private final HttpClientInformationContext httpClientInformationContext;
    private HttpClientConfigBuilderContext configurationContext;
    private HttpClientWhenBuilderContext clientWhenBuilderContext;
    private List<HttpClientWhenBuilderContext> whenBuilderContextList;

    public HttpClientGivenBuilderContext(HttpClientInformationContext httpClientInformationContext ){
        whenBuilderContextList = new  ArrayList<HttpClientWhenBuilderContext>();
        this.httpClientInformationContext=httpClientInformationContext;
    }

    @Override
    public HttpClientWhenBuilderContext given(HttpClientConfigBuilderContext configurationContext) {
        this.configurationContext = configurationContext;
        httpClientInformationContext.setClientConfigBuilderContext(this.configurationContext);
        clientWhenBuilderContext = new HttpClientWhenBuilderContext(whenBuilderContextList,httpClientInformationContext);
        return clientWhenBuilderContext;
    }
}
