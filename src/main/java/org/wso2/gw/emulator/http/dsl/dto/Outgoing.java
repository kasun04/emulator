package org.wso2.gw.emulator.http.dsl.dto;

import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class Outgoing {

    private static Outgoing outgoing;
    private HttpResponseStatus statusCode;
    private Map<String, String> cookies;
    private Map<String, String> headers;
    private String body;

    private static Outgoing getInstance() {
        outgoing = new Outgoing();
        return outgoing;
    }

    public static Outgoing response() {
        return getInstance();
    }

    public Outgoing withStatusCode(HttpResponseStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Outgoing withCookie(String name, String value) {
        if (cookies == null) {
            this.cookies = new HashMap<String, String>();
        }
        cookies.put(name, value);
        return this;
    }

    public Outgoing withCookies(Cookie... cookies) {
        if (cookies.length > 0 && this.cookies == null) {
            this.cookies = new HashMap<String, String>();
        }
        for (Cookie cookie : cookies) {
            this.cookies.put(cookie.getName(), cookie.getValue());
        }
        return this;
    }

    public Outgoing withHeader(String name, String value) {
        if (headers == null) {
            this.headers = new HashMap<String, String>();
        }
        headers.put(name, value);
        return this;
    }

    public Outgoing withHeaders(Header... headers) {
        if (headers.length > 0 && this.headers == null) {
            this.headers = new HashMap<String, String>();
        }
        for (Header header : headers) {
            this.headers.put(header.getName(), header.getValue());
        }
        return this;
    }

    public Outgoing withBody(String body) {
        this.body = body;
        return this;
    }

    public Outgoing withEmptyBody() {
        return this;
    }

    public HttpResponseStatus getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
