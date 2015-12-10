package org.wso2.gw.emulator.http.server.contexts;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.wso2.gw.emulator.core.contexts.AbstractResponseBuilderContext;
import org.wso2.gw.emulator.http.dsl.params.Cookie;
import org.wso2.gw.emulator.http.dsl.params.Header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerResponseBuilderContext extends AbstractResponseBuilderContext{

    private static HttpServerResponseBuilderContext serverResponse;
    private HttpResponseStatus statusCode = HttpResponseStatus.OK;
    private List<Cookie> cookies;
    private List<Header> headers;
    private String body;

    private static HttpServerResponseBuilderContext getInstance() {
        serverResponse = new HttpServerResponseBuilderContext();
        return serverResponse;
    }

    public static HttpServerResponseBuilderContext response() {
        return getInstance();
    }

    public HttpServerResponseBuilderContext withStatusCode(HttpResponseStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public HttpServerResponseBuilderContext withCookie(String name, String value) {
        if (cookies == null) {
            this.cookies = new ArrayList<Cookie>();
        }
        cookies.add(new Cookie(name, value));
        return this;
    }

    public HttpServerResponseBuilderContext withCookies(Cookie... cookies) {
        if (this.cookies == null) {
            this.cookies = new ArrayList<Cookie>();
        }
        if (cookies != null && cookies.length > 0) {
            this.cookies.addAll(Arrays.asList(cookies));
        }
        return this;
    }

    public HttpServerResponseBuilderContext withHeader(String name, String value) {
        if (headers == null) {
            this.headers = new ArrayList<Header>();
        }
        headers.add(new Header(name, value));
        return this;
    }

    public HttpServerResponseBuilderContext withHeaders(Header... headers) {
        if (this.headers == null) {
            this.headers = new ArrayList<Header>();
        }

        if (headers != null && headers.length > 0) {
            this.headers.addAll(Arrays.asList(headers));
        }
        return this;
    }

    public HttpServerResponseBuilderContext withBody(String body) {
        this.body = body;
        return this;
    }

    public HttpServerResponseBuilderContext withEmptyBody() {
        return this;
    }

    public HttpResponseStatus getStatusCode() {
        return statusCode;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
