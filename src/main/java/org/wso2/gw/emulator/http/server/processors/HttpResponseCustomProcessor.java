package org.wso2.gw.emulator.http.server.processors;

import org.wso2.gw.emulator.http.client.contexts.HttpResponseContext;
import org.wso2.gw.emulator.http.server.contexts.HttpRequestContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerProcessorContext;

/**
 * Created by dilshank on 12/17/15.
 */
public class HttpResponseCustomProcessor  {

    public HttpServerProcessorContext process(HttpServerProcessorContext responseContext) {

        System.out.println("server response custom processor is working");
        return responseContext;
    }
}
