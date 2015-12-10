package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractWhenBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientOperationBuilderContext;
import org.wso2.gw.emulator.http.client.contexts.HttpClientRequsetBuilderContext;

public class HttpClientWhenBuilderContext extends AbstractWhenBuilderContext<HttpClientRequsetBuilderContext> {

    @Override
    public HttpClientThenBuilderContext when(HttpClientRequsetBuilderContext requestContextBuilder) {

        return null;
    }

    @Override
    public HttpClientOperationBuilderContext operation() {
        return null;
    }
}