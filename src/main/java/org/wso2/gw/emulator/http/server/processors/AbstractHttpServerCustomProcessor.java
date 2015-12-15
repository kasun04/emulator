package org.wso2.gw.emulator.http.server.processors;


import org.wso2.gw.emulator.core.processors.AbstractCustomProcessor;
import org.wso2.gw.emulator.http.server.contexts.HttpServerProcessorContext;

public abstract class AbstractHttpServerCustomProcessor extends AbstractCustomProcessor {

    private HttpServerProcessorContext processorContext;

    public void setProcessorContext(HttpServerProcessorContext processorContext) {
        this.processorContext = processorContext;
    }
}
