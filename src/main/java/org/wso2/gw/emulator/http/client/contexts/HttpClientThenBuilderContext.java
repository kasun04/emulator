package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractResponseBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractThenBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerWhenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientThenBuilderContext extends AbstractThenBuilderContext{

    @Override
    public HttpServerWhenBuilderContext then(AbstractResponseBuilderContext responseContext) {
        return null;
    }
}
