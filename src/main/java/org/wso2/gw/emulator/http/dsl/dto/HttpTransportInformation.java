package org.wso2.gw.emulator.http.dsl.dto;

import io.netty.handler.codec.http.HttpVersion;
import org.wso2.gw.emulator.core.Emulator;
import org.wso2.gw.emulator.core.handlers.AbstractLogicHandler;

public class HttpTransportInformation {

    private String host;
    private int port;
    private String context;
    private int readingDelay;
    private int writingDelay;
    private boolean randomConnectionClose;
    private HttpVersion httpVersion;
    private Incoming incoming;
    private Outgoing outgoing;
    private AbstractLogicHandler logicHandler;


    public HttpTransportInformation() {
        this.httpVersion = HttpVersion.HTTP_1_1;
    }

    public HttpTransportInformation host(String host) {
        this.host = host;
        return this;
    }

    public HttpTransportInformation port(int port) {
        this.port = port;
        return this;
    }

    public HttpTransportInformation context(String context) {
        this.context = context;
        return this;
    }

    public HttpTransportInformation readingDelay(int readingDelay) {
        this.readingDelay = readingDelay;
        return this;
    }

    public HttpTransportInformation writingDelay(int writingDelay) {
        this.writingDelay = writingDelay;
        return this;
    }

    public HttpTransportInformation randomConnectionClose(boolean randomConnectionClose) {
        this.randomConnectionClose = randomConnectionClose;
        return this;
    }

    public HttpTransportInformation httpVersion(HttpVersion httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }

    public HttpTransportInformation when(Incoming incoming) {
        this.incoming = incoming;
        return this;
    }

    public HttpTransportInformation logic(AbstractLogicHandler logicHandler) {
        this.logicHandler = logicHandler;
        return this;
    }

    public HttpTransportInformation respond(Outgoing outgoing) {
        this.outgoing = outgoing;
        return this;
    }

    public void start() {
        try {
            new Emulator().initialize();
        } catch (Exception e) {
            e.printStackTrace();//need to handle
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
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

    public AbstractLogicHandler getLogicHandler() {
        return logicHandler;
    }

    public Incoming getIncoming() {
        return incoming;
    }

    public Outgoing getOutgoing() {
        return outgoing;
    }
}
