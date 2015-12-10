package org.wso2.gw.emulator.http.server.processors;

import org.wso2.gw.emulator.http.server.contexts.HttpServerProcessorContext;
import org.wso2.gw.emulator.http.server.contexts.HttpRequestContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerRequestBuilderContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerResponseBuilderContext;

import java.util.Map;

public class HttpRequestAssertProcessor extends AbstractServerProcessor {

    @Override
    public void process(HttpServerProcessorContext processorContext) {
        Map<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext> requestResponseCorrelation= processorContext
        .getServerInformationContext().getRequestResponseCorrelation();
        HttpRequestContext httpRequestContext = processorContext.getHttpRequestContext();
        for (Map.Entry<HttpServerRequestBuilderContext, HttpServerResponseBuilderContext> entry : requestResponseCorrelation.entrySet()) {
            if (entry.getKey().isMatch(httpRequestContext)) {
                processorContext.setSelectedResponseContext(entry.getValue());
            }
        }
    }
}
