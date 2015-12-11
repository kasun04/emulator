package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractClientOperationBuilderContext;

public class HttpClientOperationBuilderContext extends AbstractClientOperationBuilderContext {

    @Override
    public HttpClientOperationBuilderContext send() {
        return this;
    }
}
