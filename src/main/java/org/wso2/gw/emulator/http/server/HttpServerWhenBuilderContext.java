package org.wso2.gw.emulator.http.server;

import org.wso2.gw.emulator.core.AbstractWhenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerWhenBuilderContext extends AbstractWhenBuilderContext<HttpServerRequestBuilderContext>{

    private HttpServerThenBuilderContext thenBuilderContext;

    @Override
    public HttpServerThenBuilderContext when(HttpServerRequestBuilderContext requestContext) {

        thenBuilderContext = new HttpServerThenBuilderContext();
        return thenBuilderContext;
    }
}
