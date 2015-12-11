package org.wso2.gw.emulator.http.client.contexts;

import io.netty.handler.codec.http.HttpMethod;
import org.wso2.gw.emulator.core.contexts.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.http.params.Cookie;
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
    private List<QueryParameter> queryParameters;
    private List<Cookie> cookies;
    private Pattern pathRegex;


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
        QueryParameter queryParameter = new QueryParameter(name, value);

        if(queryParameters == null) {
            queryParameters = new ArrayList<QueryParameter>();
        }
        queryParameters.add(queryParameter);
        return this;
    }

    public HttpClientRequestBuilderContext withCookie(String name, String value) {
       Cookie cookie = new Cookie(name, value);

        if(cookies == null) {
            cookies = new ArrayList<Cookie>();
        }
        cookies.add(cookie);
        return this;
    }

    public void buildPathRegex(String context) {
        this.context = context;
        String regex = buildRegex(context, path);
        this.pathRegex = Pattern.compile(regex);
    }

    private String buildRegex(String context, String path) {
        String fullPath = "";

        if ((context == null || context.isEmpty()) && (path == null || path.isEmpty())) {
            return ".*";
        }

        if (context != null && !context.isEmpty()) {
            fullPath = context;

            if (!fullPath.startsWith("/")) {
                fullPath = "/" + fullPath;
            }

            if (!fullPath.endsWith("/")) {
                fullPath = fullPath + "/";
            }
        } else {
            fullPath = ".*";
        }

        if (path != null && !path.isEmpty()) {
            if (fullPath.endsWith("/") && path.startsWith("/")) {
                fullPath = fullPath + path.substring(1);
            } else if (fullPath.endsWith("/") && !path.startsWith("/")) {
                fullPath = fullPath + path;
            } else if (!fullPath.endsWith("/") && path.startsWith("/")) {
                fullPath = fullPath + path;
            } else {
                fullPath = fullPath + "/" + path;
            }
        } else {
            fullPath = fullPath + ".*";
        }

        if (fullPath.endsWith("/")) {
            fullPath = fullPath.substring(0, fullPath.length() - 1);
        }
        return "^" + fullPath + "$";
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

    public List<QueryParameter> getQueryParameters() {
        return queryParameters;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }
}
