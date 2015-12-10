package org.wso2.gw.emulator.http.server.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractEmulatorBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractResponseBuilderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerEmulatorBuilderContext extends AbstractEmulatorBuilderContext {

    private Map<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext> requestResponseCorrelation = new HashMap<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext>();
    private HttpServerConfigBuilderContext serverConfigurationBuilderContext;


}
