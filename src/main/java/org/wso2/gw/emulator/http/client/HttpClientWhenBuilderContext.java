package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.core.AbstractWhenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public abstract class HttpClientWhenBuilderContext extends AbstractWhenBuilderContext<HttpClientRequsetBuilderContext> {

    @Override
    public HttpClientThenBuilderContext when(HttpClientRequsetBuilderContext requestContextBuilder) {

        return null;
    }
}