package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.AbstractGivenBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerGivenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientGivenBuilderContext extends AbstractGivenBuilderContext{
    @Override
    public HttpServerGivenBuilderContext given(AbstractConfigurationBuilderContext configurationContext) {

        return null;
    }
}
