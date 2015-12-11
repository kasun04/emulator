package org.wso2.gw.emulator.http.client.contexts;

import io.netty.handler.codec.http.HttpMethod;
import org.wso2.gw.emulator.core.contexts.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.http.params.Header;
import org.wso2.gw.emulator.http.params.QueryParameter;
import org.wso2.gw.emulator.http.server.contexts.HttpRequestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientRequestBuilderContext extends AbstractRequestBuilderContext {

    private static HttpClientRequestBuilderContext clientRequest;
    private HttpMethod method;
    private String path;
    private String body;
    private String context;
    private List<Header> headers;
    private QueryParameter queryParameter;


    private static HttpClientRequestBuilderContext getInstance() {
        clientRequest = new HttpClientRequestBuilderContext();
        return clientRequest;
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
        Header header = new Header(name, value);

        if(headers == null) {
            headers = new ArrayList<Header>();
        }

        headers.add(header);
        return this;
    }

    public HttpClientRequestBuilderContext withQueryParameter(String name, String value) {
        this.queryParameter = new QueryParameter(name, value);
        return this;
    }

    public String getContext() {
        return context;
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

    public List<Header> getHeaders() {
        return headers;
    }
}
