package org.wso2.gw.emulator.http.client.processors;


import org.wso2.gw.emulator.core.processors.AbstractCustomProcessor;
import org.wso2.gw.emulator.http.client.contexts.HttpClientRequestProcessorContext;

public abstract class AbstractHttpClientCustomProcessor extends AbstractCustomProcessor {

    private HttpClientRequestProcessorContext processorContext;

    public void setProcessorContext(HttpClientRequestProcessorContext processorContext) {
        this.processorContext = processorContext;
    }
}
