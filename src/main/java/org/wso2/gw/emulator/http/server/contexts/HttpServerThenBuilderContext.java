package org.wso2.gw.emulator.http.server.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractThenBuilderContext;

import java.util.List;


/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerThenBuilderContext extends AbstractThenBuilderContext<HttpServerResponseBuilderContext>{
    private final HttpServerRequestBuilderContext requestContext;
        private final HttpServerInformationContext httpServerInformationContext;
    private HttpServerWhenBuilderContext whenBuilderContext;
        private List<HttpServerWhenBuilderContext> whenBuilderContextList;
        private HttpServerResponseBuilderContext responseContext;

                public HttpServerThenBuilderContext(List<HttpServerWhenBuilderContext> whenBuilderContextList, HttpServerRequestBuilderContext requestContext, HttpServerInformationContext httpServerInformationContext){
                this.requestContext = requestContext;
                this.httpServerInformationContext = httpServerInformationContext;
                this.whenBuilderContextList = whenBuilderContextList;
                //this.whenBuilderContextList.add(this);
                    }

    @Override
    public HttpServerWhenBuilderContext then(HttpServerResponseBuilderContext responseContext) {

                whenBuilderContext = new HttpServerWhenBuilderContext(whenBuilderContextList,httpServerInformationContext);
                this.responseContext = responseContext;
                whenBuilderContext = new HttpServerWhenBuilderContext(whenBuilderContextList,httpServerInformationContext);
                this.httpServerInformationContext.addCorrelation(requestContext,responseContext);
        return whenBuilderContext;
    }

        /*@Override
        public void operation(OperationType operationType) {
            public HttpServerResponseBuilderContext getResponseContext() {
                    return responseContext;
                }


                // return httpProtocolEmulator;
                    }*/






}
