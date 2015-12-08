package org.wso2.gw.emulator.core;

import org.wso2.gw.emulator.http.dsl.consumer.IncomingMessage;
import org.wso2.gw.emulator.http.dsl.consumer.OutgoingMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamile on 12/7/15.
 */
public abstract class AbstractEmulatorBuilderContext{

        private Map<AbstractRequestBuilderContext, AbstractResponseBuilderContext> requestResponseCorelation = new HashMap<AbstractRequestBuilderContext, AbstractResponseBuilderContext>();
        private AbstractConfigurationBuilderContext abstractConfigurationBuilderContext;


    public Map<AbstractRequestBuilderContext, AbstractResponseBuilderContext> getRequestResponseCorelation() {
        return requestResponseCorelation;
    }

    public void setRequestResponseCorelation(Map<AbstractRequestBuilderContext, AbstractResponseBuilderContext> requestResponseCorelation) {
        this.requestResponseCorelation = requestResponseCorelation;
    }

    public AbstractConfigurationBuilderContext getAbstractConfigurationBuilderContext() {
        return abstractConfigurationBuilderContext;
    }

    public void setAbstractConfigurationBuilderContext(AbstractConfigurationBuilderContext abstractConfigurationBuilderContext) {
        this.abstractConfigurationBuilderContext = abstractConfigurationBuilderContext;
    }


}
