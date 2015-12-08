package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.core.AbstractThenBuilderContext;
import org.wso2.gw.emulator.core.AbstractWhenBuilderContext;
import org.wso2.gw.emulator.core.OperationType;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientWhenBuilderContext extends AbstractWhenBuilderContext<HttpClientRequestBuilderContext>{
    private HttpClientThenBuilderContext clientThenBuilderContext;

    @Override
    public HttpClientThenBuilderContext when(HttpClientRequestBuilderContext requestContext) {

        clientThenBuilderContext = new HttpClientThenBuilderContext();
        return clientThenBuilderContext;
    }

    @Override
    public void operation(OperationType operationType) {

    }


}
