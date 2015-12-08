package org.wso2.gw.emulator.http.server;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpVersion;
import org.wso2.gw.emulator.core.AbstractConfigurationBuilderContext;
import org.wso2.gw.emulator.core.AbstractProtocolEmulator;
import org.wso2.gw.emulator.http.dsl.consumer.IncomingMessage;
import org.wso2.gw.emulator.http.dsl.consumer.OutgoingMessage;

import java.util.Map;

/**
 * Created by chamile on 12/7/15.
 */
public class HttpServerConfigBuilderContext extends AbstractConfigurationBuilderContext {
    private static HttpServerConfigBuilderContext config;
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


    private static HttpServerConfigBuilderContext getInstance() {
        config = new HttpServerConfigBuilderContext();
        return config;
    }

    public static HttpServerConfigBuilderContext configure() {
        return getInstance();
    }

    public HttpServerConfigBuilderContext host(String host) {
        this.host=host;
        return this;
    }

    public HttpServerConfigBuilderContext port(int port) {
        this.port=port;
        return this;
    }

    public HttpServerConfigBuilderContext context(String context) {
        this.context = context;
        return this;
    }

    public HttpServerConfigBuilderContext readingDelay(int readingDelay) {
        this.readingDelay = readingDelay;
        return this;
    }

    public HttpServerConfigBuilderContext writingDelay(int writingDelay) {
        this.writingDelay = writingDelay;
        return this;
    }

    public HttpServerConfigBuilderContext randomConnectionClose(boolean randomConnectionClose) {
        this.randomConnectionClose = randomConnectionClose;
        return this;
    }

    public HttpServerConfigBuilderContext httpVersion(HttpVersion httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }



    public HttpServerConfigBuilderContext logic(ChannelInboundHandlerAdapter logicHandler) {
        this.logicHandler = logicHandler;
        return this;
    }

/*

    public AbstractProtocolEmulator operations() {
        return httpProtocolEmulator;
    }

    public String getContext() {
        return context;
    }

    public int getReadingDelay() {
        return readingDelay;
    }

    public int getWritingDelay() {
        return writingDelay;
    }

    public boolean isRandomConnectionClose() {
        return randomConnectionClose;
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public ChannelInboundHandlerAdapter getLogicHandler() {
        return logicHandler;
    }

    public IncomingMessage getIncoming() {
        return incoming;
    }

    public Map<IncomingMessage, OutgoingMessage> getInOutCorrelation() {
        return inOutCorrelation;
    }
*/
}
