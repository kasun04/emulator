package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractResponseBuilderContext;
import org.wso2.gw.emulator.core.AbstractThenBuilderContext;
import org.wso2.gw.emulator.http.server.HttpServerWhenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientThenBuilderContext extends AbstractThenBuilderContext{

    @Override
    public HttpServerWhenBuilderContext then(AbstractResponseBuilderContext responseContext) {
        return null;
    }
}
