package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractGivenBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientWhenBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerConfigBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerWhenBuilderContext;

import java.util.ArrayList;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientGivenBuilderContext extends AbstractGivenBuilderContext<HttpClientConfigBuilderContext>{

    private final ArrayList<HttpClientWhenBuilderContext> whenBuilderContextList;
    private HttpClientInformationContext httpClientInformationContext;
    private HttpClientConfigBuilderContext configurationContext;
    private HttpClientWhenBuilderContext whenBuilderContext;

    public HttpClientGivenBuilderContext(HttpClientInformationContext httpClientInformationContext) {
        whenBuilderContextList = new ArrayList<HttpClientWhenBuilderContext>();
        this.httpClientInformationContext = httpClientInformationContext;
    }
    @Override
    public HttpClientWhenBuilderContext given(HttpClientConfigBuilderContext configurationContext) {
        this.configurationContext = configurationContext;
        httpClientInformationContext.setClientConfigBuilderContext(this.configurationContext);
        whenBuilderContext = new HttpClientWhenBuilderContext(whenBuilderContextList, httpClientInformationContext);
        return whenBuilderContext;
    }
}
