package org.wso2.gw.emulator.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import org.wso2.gw.emulator.core.handlers.ReadingDelayHandler;
import org.wso2.gw.emulator.core.handlers.RequestFilterHandler;
import org.wso2.gw.emulator.core.handlers.RequestInfoPopulateHandler;
import org.wso2.gw.emulator.core.handlers.ResponseHandler;
import org.wso2.gw.emulator.core.handlers.WaitingDelayHandler;

public class EmulatorInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;
    private final EmulatorContext emulatorContext;

    public EmulatorInitializer(final EmulatorContext emulatorContext, SslContext sslCtx) {
        this.sslCtx = sslCtx;
        this.emulatorContext = emulatorContext;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast("sslHandler", sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("encoder", new HttpResponseEncoder());
        // pipeline.addLast("requestFilterHandler", new RequestFilterHandler(emulatorContext));
        //pipeline.addLast("readDelayHandler", new ReadingDelayHandler(emulatorContext));
        //pipeline.addLast("requestInfoPopulateHandler", new RequestInfoPopulateHandler(emulatorContext));
        if (emulatorContext.getHttpTransportInformation().getLogicHandler() != null) {
            pipeline.addLast("logicHandler", emulatorContext.getHttpTransportInformation().getLogicHandler());
        }
        //pipeline.addLast("waitingHandler", new WaitingDelayHandler(emulatorContext));
        pipeline.addLast("responseHandler", new ResponseHandler(emulatorContext));

    }
}
