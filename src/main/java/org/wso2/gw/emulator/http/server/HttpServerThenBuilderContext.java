package org.wso2.gw.emulator.http.server;

import org.wso2.gw.emulator.core.AbstractThenBuilderContext;
import org.wso2.gw.emulator.core.OperationType;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerThenBuilderContext extends AbstractThenBuilderContext<HttpServerResponseBuilderContext>{
    private HttpServerWhenBuilderContext whenBuilderContext;

    @Override
    public HttpServerWhenBuilderContext then(HttpServerResponseBuilderContext responseContext) {

        whenBuilderContext = new HttpServerWhenBuilderContext();
        return whenBuilderContext;
    }

    @Override
    public void operation(OperationType operationType) {


        // return httpProtocolEmulator;
    }




}
