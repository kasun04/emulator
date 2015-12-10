package org.wso2.gw.emulator.http.client;

import io.netty.handler.codec.http.HttpMethod;
import org.wso2.gw.emulator.core.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.http.dsl.params.Cookie;
import org.wso2.gw.emulator.http.dsl.params.Header;
import org.wso2.gw.emulator.http.dsl.params.QueryParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientRequestBuilderContext extends AbstractRequestBuilderContext {


    private static HttpClientRequestBuilderContext ClientRequestBuilderContext;
    private HttpMethod method;
    private String path;
    private String body;
    private Header header;
    private QueryParameter queryParameter;
    private List<Header> headers;
    private List<QueryParameter> queryParameters;
    private List<Cookie> cookies;

    private static HttpClientRequestBuilderContext getInstance() {
        ClientRequestBuilderContext = new HttpClientRequestBuilderContext();
        return ClientRequestBuilderContext;
    }

    public static HttpClientRequestBuilderContext request() {
        return getInstance();
    }

    public HttpClientRequestBuilderContext withMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpClientRequestBuilderContext withPath(String path) {
        this.path = path;
        return this;
    }

    public HttpClientRequestBuilderContext withBody(String body) {
        this.body = body;
        return this;
    }

    public HttpClientRequestBuilderContext withHeader(String name, String value) {
        header = new Header(name, value);
        return this;
    }

    public HttpClientRequestBuilderContext withHeaders(Header... headers) {
        this.headers = Arrays.asList(headers);
        return this;
    }

    public HttpClientRequestBuilderContext withQueryParameter(String name, String value) {
        this.queryParameter = new QueryParameter(name, value);
        return this;
    }

    public HttpClientRequestBuilderContext withQueryParameters(QueryParameter... queryParameters) {
        this.queryParameters = Arrays.asList(queryParameters);
        return this;
    }

    public HttpClientRequestBuilderContext withCookie(String name, String value) {
        if (cookies == null) {
            this.cookies = new ArrayList<Cookie>();
        }
        this.cookies.add(new Cookie(name, value));
        return this;
    }

    public HttpClientRequestBuilderContext withCookies(Cookie... cookies) {
        this.cookies = Arrays.asList(cookies);
        return this;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getBody() {
        return body;
    }

    public List<QueryParameter> getQueryParameters() {
        return queryParameters;
    }


}
