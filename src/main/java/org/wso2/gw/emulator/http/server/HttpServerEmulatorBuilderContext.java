package org.wso2.gw.emulator.http.server;

import org.wso2.gw.emulator.core.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.AbstractEmulatorBuilderContext;
import org.wso2.gw.emulator.core.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.core.AbstractResponseBuilderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerEmulatorBuilderContext extends AbstractEmulatorBuilderContext {

    private Map<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext> requestResponseCorrelation = new HashMap<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext>();
    private HttpServerConfigBuilderContext serverConfigurationBuilderContext;


}
