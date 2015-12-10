package org.wso2.gw.emulator.http.server.processors;


import org.wso2.gw.emulator.http.server.contexts.HttpServerProcessorContext;

public abstract class AbstractServerProcessor {

    public abstract void process(HttpServerProcessorContext processorContext);

}
