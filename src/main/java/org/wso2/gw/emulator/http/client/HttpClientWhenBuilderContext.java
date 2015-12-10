package org.wso2.gw.emulator.http.client;

import org.wso2.gw.emulator.core.AbstractWhenBuilderContext;
import org.wso2.gw.emulator.core.OperationType;

import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientWhenBuilderContext extends AbstractWhenBuilderContext<HttpClientRequestBuilderContext> {

    private final HttpClientInformationContext httpClientInformationContext;
    private final List<HttpClientWhenBuilderContext> whenBuilderContextList;
    private HttpClientThenBuilderContext clientThenBuilderContext;

    public HttpClientWhenBuilderContext(List<HttpClientWhenBuilderContext> whenBuilderContextList, HttpClientInformationContext httpClientInformationContext){
        this.httpClientInformationContext = httpClientInformationContext;
        this.whenBuilderContextList = whenBuilderContextList;
        this.whenBuilderContextList.add(this);
    }
    @Override
    public HttpClientThenBuilderContext when(HttpClientRequestBuilderContext requestContextBuilder) {
        clientThenBuilderContext = new HttpClientThenBuilderContext(whenBuilderContextList,requestContextBuilder,httpClientInformationContext);
        return clientThenBuilderContext;
    }


    @Override
    public void operation(OperationType operationType) {
        System.out.println("debug point 2");
        // return httpProtocolEmulator;
    }

}