package org.wso2.gw.emulator.http.dsl.consumer;

import io.netty.handler.codec.http.HttpMethod;
import org.wso2.gw.emulator.core.AbstractBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public class testConfig extends AbstractBuilderContext {

    private static testConfig test;
    private HttpMethod method;
    private String context;
    private int writingDelay;

    private static testConfig getInstance() {
        test = new testConfig();
        return test;
    }

    public static testConfig configure() {
        return getInstance();
    }

    public testConfig withMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public testConfig host(String host) {
        super.host(host);
        return this;
    }

    public testConfig port(int port) {
        super.port(port);
        return this;
    }

    public testConfig context(String context) {
        this.context = context;
        return this;
    }

    public testConfig writingDelay(int writingDelay) {
        this.writingDelay = writingDelay;
        return this;
    }

}