package org.wso2.gw.emulator.http.client.contexts;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.wso2.gw.emulator.core.contexts.AbstractResponseBuilderContext;
import org.wso2.gw.emulator.http.dsl.params.Header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientResponseBuilderContext extends AbstractResponseBuilderContext {

    private static HttpClientResponseBuilderContext outgoing;
    private HttpResponseStatus statusCode;
    private List<Header> headers;
    private String body;

    private static HttpClientResponseBuilderContext getInstance() {
        outgoing = new HttpClientResponseBuilderContext();
        return outgoing;
    }

    public static HttpClientResponseBuilderContext response() {
        return getInstance();
    }

    public HttpClientResponseBuilderContext withStatusCode(HttpResponseStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public HttpClientResponseBuilderContext withHeader(String name, String value) {
        if (headers == null) {
            this.headers = new ArrayList<Header>();
        }
        headers.add(new Header(name, value));
        return this;
    }

    public HttpClientResponseBuilderContext withHeaders(Header... headers) {
        if (this.headers == null) {
            this.headers = new ArrayList<Header>();
        }

        if (headers != null && headers.length > 0) {
            this.headers.addAll(Arrays.asList(headers));
        }
        return this;
    }

    public HttpClientResponseBuilderContext withBody(String body) {
        this.body = body;
        return this;
    }

    public HttpResponseStatus getStatusCode() {
        return statusCode;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

}
