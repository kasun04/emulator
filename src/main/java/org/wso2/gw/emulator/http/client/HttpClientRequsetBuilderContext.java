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
public class HttpClientRequsetBuilderContext extends AbstractRequestBuilderContext {

    private static HttpClientRequsetBuilderContext incoming;
    private String path;
    private HttpMethod method;
    private String body;
    private List<Header> headers;
    private List<QueryParameter> queryParameters;
    private List<Cookie> cookies;


    private static HttpClientRequsetBuilderContext getInstance() {
        incoming = new HttpClientRequsetBuilderContext();
        return incoming;
    }

    public static HttpClientRequsetBuilderContext request() {
        return getInstance();
    }

    public HttpClientRequsetBuilderContext withMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpClientRequsetBuilderContext withPath(String path) {
        this.path = path;
        return this;
    }

    public HttpClientRequsetBuilderContext withBody(String body) {
        this.body = body;
        return this;
    }

    public HttpClientRequsetBuilderContext withHeader(String name, String value) {
        if (headers == null) {
            this.headers = new ArrayList<Header>();
        }
        Header header = new Header(name, value);
        this.headers.add(header);
        return this;
    }

    public HttpClientRequsetBuilderContext withHeaders(Header... headers) {
        this.headers = Arrays.asList(headers);
        return this;
    }

    public HttpClientRequsetBuilderContext withQueryParameter(String name, String value) {
        if (queryParameters == null) {
            this.queryParameters = new ArrayList<QueryParameter>();
        }
        this.queryParameters.add(new QueryParameter(name, value));
        return this;
    }

    public HttpClientRequsetBuilderContext withQueryParameters(QueryParameter... queryParameters) {
        this.queryParameters = Arrays.asList(queryParameters);
        return this;
    }

    public HttpClientRequsetBuilderContext withCookie(String name, String value) {
        if (cookies == null) {
            this.cookies = new ArrayList<Cookie>();
        }
        this.cookies.add(new Cookie(name, value));
        return this;
    }

    public HttpClientRequsetBuilderContext withCookies(Cookie... cookies) {
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
