package org.wso2.gw.emulator.core.contexts;

/**
 * Created by chamile on 12/7/15.
 */
public class AbstractEmulatorBuilderContext {

    private String host;
    private Integer port;

    public AbstractEmulatorBuilderContext host(String host) {
        this.host = host;
        return this;
    }

    public AbstractEmulatorBuilderContext port(int port) {
        this.port = port;
        return this;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }
}
