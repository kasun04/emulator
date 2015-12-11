package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractGivenBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientWhenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientGivenBuilderContext extends AbstractGivenBuilderContext{

    private HttpClientInformationContext httpClientInformationContext;

    public HttpClientGivenBuilderContext(HttpClientInformationContext httpClientInformationContext) {
        this.httpClientInformationContext = httpClientInformationContext;
    }
    @Override
    public HttpClientWhenBuilderContext given(AbstractConfigurationBuilderContext configurationContext) {

        return null;
    }
}
