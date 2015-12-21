package org.wso2.gw.emulator.http.server.processors;

import org.wso2.gw.emulator.http.client.contexts.HttpResponseContext;
import org.wso2.gw.emulator.http.server.contexts.HttpRequestContext;
import org.wso2.gw.emulator.http.server.contexts.HttpServerProcessorContext;

/**
 * Created by dilshank on 12/18/15.
 */
public class HttpRequestCustomProcessor {

    public HttpServerProcessorContext process(HttpServerProcessorContext requestContext) {

        System.out.println("server request custom processor is working");

        return requestContext;
    }
}
