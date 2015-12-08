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

    private Map<AbstractRequestBuilderContext, AbstractResponseBuilderContext> requestResponseCorelation = new HashMap<AbstractRequestBuilderContext, AbstractResponseBuilderContext>();
    private AbstractConfigurationBuilderContext abstractConfigurationBuilderContext;

    public HttpServerEmulatorBuilderContext mapping(HttpServerRequestBuilderContext requestBuilderContext,HttpServerResponseBuilderContext responseBuilderContext){
        this.requestResponseCorelation.put(requestBuilderContext,responseBuilderContext);
        return this;
    }

}
