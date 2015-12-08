package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractResponseBuilderContext;
import org.wso2.gw.emulator.core.AbstractThenBuilderContext;
import org.wso2.gw.emulator.core.OperationType;
import org.wso2.gw.emulator.http.server.HttpServerWhenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientThenBuilderContext extends AbstractThenBuilderContext<HttpClientResponseBuilderContext>{

    private HttpClientWhenBuilderContext clientWhenBuilderContext;

    @Override
    public HttpClientWhenBuilderContext then(HttpClientResponseBuilderContext responseContext) {

        clientWhenBuilderContext = new HttpClientWhenBuilderContext();
        return clientWhenBuilderContext;
    }

    @Override
    public void operation(OperationType operationType) {

    }
}
