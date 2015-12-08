package org.wso2.gw.emulator.http.client;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpVersion;
import org.wso2.gw.emulator.core.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.AbstractProtocolEmulator;
import org.wso2.gw.emulator.http.dsl.consumer.IncomingMessage;
import org.wso2.gw.emulator.http.dsl.consumer.OutgoingMessage;
import org.wso2.gw.emulator.http.server.HttpServerRequestBuilderContext;

import java.util.Map;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpClientConfigBuilderContext extends AbstractConfigurationBuilderContext {
    private static HttpClientConfigBuilderContext config;
    private String host;
    private int port;
    private String context;
    private int readingDelay;
    private int writingDelay;
    private boolean randomConnectionClose;
    private ChannelInboundHandlerAdapter logicHandler;
    private HttpVersion httpVersion;
    private HttpServerRequestBuilderContext incoming;
    private AbstractProtocolEmulator httpProtocolEmulator;
    private Map<IncomingMessage,OutgoingMessage> inOutCorrelation;

    /*public HttpServerConfigBuilderContext(HTTPProtocolEmulator httpProtocolEmulator) {
        this.httpProtocolEmulator = httpProtocolEmulator;
        this.httpVersion = HttpVersion.HTTP_1_1;
    }*/


    private static HttpClientConfigBuilderContext getInstance() {
        config = new HttpClientConfigBuilderContext();
        return config;
    }

    public static HttpClientConfigBuilderContext configure() {
        return getInstance();
    }

    public HttpClientConfigBuilderContext host(String host) {
        this.host=host;
        return this;
    }

    public HttpClientConfigBuilderContext port(int port) {
        this.port=port;
        return this;
    }

    public HttpClientConfigBuilderContext context(String context) {
        this.context = context;
        return this;
    }

    public HttpClientConfigBuilderContext readingDelay(int readingDelay) {
        this.readingDelay = readingDelay;
        return this;
    }

    public HttpClientConfigBuilderContext writingDelay(int writingDelay) {
        this.writingDelay = writingDelay;
        return this;
    }

    public HttpClientConfigBuilderContext randomConnectionClose(boolean randomConnectionClose) {
        this.randomConnectionClose = randomConnectionClose;
        return this;
    }

    public HttpClientConfigBuilderContext httpVersion(HttpVersion httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }



    public HttpClientConfigBuilderContext logic(ChannelInboundHandlerAdapter logicHandler) {
        this.logicHandler = logicHandler;
        return this;
    }


}
