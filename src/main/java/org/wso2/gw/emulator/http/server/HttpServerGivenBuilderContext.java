package org.wso2.gw.emulator.http.server;

import org.wso2.gw.emulator.core.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.AbstractGivenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerGivenBuilderContext extends AbstractGivenBuilderContext<HttpServerConfigBuilderContext>{

    private AbstractConfigurationBuilderContext configurationContext;
    private HttpServerWhenBuilderContext whenBuilderContext;

    @Override
    public HttpServerWhenBuilderContext given(HttpServerConfigBuilderContext configurationContext) {

        whenBuilderContext = new HttpServerWhenBuilderContext();

        return whenBuilderContext;
    }


}
