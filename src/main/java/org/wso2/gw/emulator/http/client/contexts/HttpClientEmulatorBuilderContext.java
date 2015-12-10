package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractEmulatorBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.core.contexts.AbstractResponseBuilderContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientEmulatorBuilderContext extends AbstractEmulatorBuilderContext {

    private Map<AbstractRequestBuilderContext, AbstractResponseBuilderContext> requestResponseCorelation = new HashMap<AbstractRequestBuilderContext, AbstractResponseBuilderContext>();
    private AbstractConfigurationBuilderContext abstractConfigurationBuilderContext;

    
}
