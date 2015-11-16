package org.wso2.gw.emulator.http.dsl.dto;

import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class Incoming {

    private static Incoming incoming;
    private HttpMethod method;
    private String path;
    private String body;
    private DecoderResult requestBody;
    private Map<String, String> headers;
    private Map<String, String> queryParameters;
    private boolean contextMatching;

    private static Incoming getInstance() {
        incoming = new Incoming();
        return incoming;
    }

    public static Incoming request() {
        return getInstance();
    }

    public Incoming withMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public Incoming withPath(String path) {
        this.path = path;
        return this;
    }

    public Incoming withBody(String body) {
        this.body = body;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }

    public void addQueryParameter(String name, String value) {
        if (this.queryParameters == null) {
            this.queryParameters = new HashMap<String, String>();
        }
        this.queryParameters.put(name, value);
    }

    public Map<String, String> getQueryParameters() {
        return queryParameters;
    }

    public void addHeader(String name, String value) {
        if (this.headers == null) {
            this.headers = new HashMap<String, String>();
        }
        this.queryParameters.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean isContextMatching() {
        return contextMatching;
    }

    public void setContextMatching(boolean contextMatching) {
        this.contextMatching = contextMatching;
    }

    public DecoderResult getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(DecoderResult requestBody) {
        this.requestBody = requestBody;
    }
}
