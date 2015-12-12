package org.wso2.gw.emulator.http.client.contexts;

import org.wso2.gw.emulator.core.contexts.AbstractConfigurationBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientConfigBuilderContext extends AbstractConfigurationBuilderContext {

    private static HttpClientConfigBuilderContext clientConfigBuilderContext;
    private String host;
    private int port;
    private String context;

    private static HttpClientConfigBuilderContext getInstance() {
        clientConfigBuilderContext = new HttpClientConfigBuilderContext();
        return clientConfigBuilderContext;
    }

    public static HttpClientConfigBuilderContext configure() {
        return getInstance();
    }

    @Override
    public HttpClientConfigBuilderContext host(String host) {
        this.host=host;
        return this;
    }

    @Override
    public HttpClientConfigBuilderContext port(int port) {
        this.port=port;
        return this;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public String getContext() {
        return context;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
