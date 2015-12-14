package org.wso2.gw.emulator.http.server.contexts;

import io.netty.handler.codec.http.HttpMethod;
import org.wso2.gw.emulator.core.contexts.AbstractRequestBuilderContext;
import org.wso2.gw.emulator.http.params.Cookie;
import org.wso2.gw.emulator.http.params.Header;
import org.wso2.gw.emulator.http.params.QueryParameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class HttpServerRequestBuilderContext extends AbstractRequestBuilderContext {

    private static HttpServerRequestBuilderContext serverRequest;
    private HttpMethod method;
    private String path;
    private String body;
    private String context;
    private Pattern pathRegex;
    private Header header;
    private QueryParameter queryParameter;
    private List<Header> headers;
    private List<QueryParameter> queryParameters;
    private List<Cookie> cookies;


    private static HttpServerRequestBuilderContext getInstance() {
        serverRequest = new HttpServerRequestBuilderContext();
        return serverRequest;
    }

    public static HttpServerRequestBuilderContext request() {
        return getInstance();
    }

    public HttpServerRequestBuilderContext withMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpServerRequestBuilderContext withPath(String path) {
        this.path = path;
        return this;
    }

    public HttpServerRequestBuilderContext withBody(String body) {
        this.body = body;
        return this;
    }

    public HttpServerRequestBuilderContext withHeader(String name, String value) {
        header = new Header(name, value);
        return this;
    }

    public HttpServerRequestBuilderContext withHeaders(Header... headers) {
        this.headers = Arrays.asList(headers);
        return this;
    }


    public HttpServerRequestBuilderContext withQueryParameter(String name, String value) {
        this.queryParameter = new QueryParameter(name, value);
        return this;
    }


    public HttpServerRequestBuilderContext withQueryParameters(QueryParameter... queryParameters) {
        this.queryParameters = Arrays.asList(queryParameters);
        return this;
    }

    public HttpServerRequestBuilderContext withCookie(String name, String value) {
        if (cookies == null) {
            this.cookies = new ArrayList<Cookie>();
        }
        this.cookies.add(new Cookie(name, value));
        return this;
    }

    public HttpServerRequestBuilderContext withCookies(Cookie... cookies) {
        this.cookies = Arrays.asList(cookies);
        return this;
    }

    public HttpServerRequestBuilderContext withCustomProcessor(String CustomRequestProcessor) {
        return this;
    }

    public boolean isMatch(HttpRequestContext requestContext) {
        if (isContextMatch(requestContext) && isHttpMethodMatch(requestContext) && isRequestContentMatch(requestContext) &&
                isHeadersMatch(requestContext) && isQueryParameterMatch(requestContext)) {
            return true;
        }
        return false;
    }

    public void buildPathRegex(String context) {
        this.context = context;
        String regex = buildRegex(context, path);
        this.pathRegex = Pattern.compile(regex);
    }

    private boolean isContextMatch(HttpRequestContext requestContext) {
        this.context = extractContext(requestContext.getUri());
        return pathRegex.matcher(context).find();
    }


    private boolean isHttpMethodMatch(HttpRequestContext requestContext) {
        if (method == null) {
            return true;
        }

        if (method.equals(requestContext.getHttpMethod())) {
            return true;
        }
        return false;
    }

    private boolean isRequestContentMatch(HttpRequestContext requestContext) {
        if (body == null || body.isEmpty()) {
            return true;
        }

        if (body.equalsIgnoreCase(requestContext.getRequestBody())) {
            return true;
        }
        return false;
    }

    private boolean isHeadersMatch(HttpRequestContext requestContext) {
        if (header == null) {
            return true;
        }

        Map<String, List<String>> headerParameters = requestContext.getHeaderParameters();
        List<String> headerValues = headerParameters.get(header.getName());

        if (headerParameters == null || headerValues == null || headerValues.isEmpty()) {
            return false;
        }

        for (String value : headerValues) {
            if (value.equalsIgnoreCase(header.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isQueryParameterMatch(HttpRequestContext requestContext) {
        if (queryParameter == null) {
            return true;
        }

        Map<String, List<String>> queryParameters = requestContext.getQueryParameters();
        List<String> queryValues = queryParameters.get(queryParameter.getName());

        if (queryParameters == null || queryValues == null || queryValues.isEmpty()) {
            return false;
        }

        for (String value : queryValues) {
            if (value.equalsIgnoreCase(queryParameter.getValue())) {
                return true;
            }
        }
        return false;
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

    private String extractContext(String uri) {
        if (uri == null || uri.isEmpty()) {
            return null;
        }
        if (!uri.contains("?")) {
            if (!uri.endsWith("/")) {
                uri = uri + "/";
            }
            return uri;
        }
        uri = uri.split("\\?")[0];
        if (!uri.endsWith("/")) {
            uri = uri + "/";
        }
        return uri;
    }
}
