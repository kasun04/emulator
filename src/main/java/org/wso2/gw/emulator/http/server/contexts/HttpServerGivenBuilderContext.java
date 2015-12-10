package org.wso2.gw.emulator.http.server.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractGivenBuilderContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerGivenBuilderContext extends AbstractGivenBuilderContext<HttpServerConfigBuilderContext>{

    private final HttpServerInformationContext httpServerInformationContext;
    private HttpServerConfigBuilderContext configurationContext;
    private HttpServerWhenBuilderContext whenBuilderContext;
    private List<HttpServerWhenBuilderContext> whenBuilderContextList;

    public HttpServerGivenBuilderContext(HttpServerInformationContext httpServerInformationContext){
        whenBuilderContextList = new ArrayList<HttpServerWhenBuilderContext>();
        this.httpServerInformationContext = httpServerInformationContext;
    }

    @Override
    public HttpServerWhenBuilderContext given(HttpServerConfigBuilderContext configurationContext) {
        this.configurationContext = configurationContext;
        httpServerInformationContext.setServerConfigBuilderContext(this.configurationContext);
        whenBuilderContext = new HttpServerWhenBuilderContext(whenBuilderContextList,httpServerInformationContext);
        return whenBuilderContext;
    }

        public HttpServerConfigBuilderContext getConfigurationContext() {
                return configurationContext;
            }


}
