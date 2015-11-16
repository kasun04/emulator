package org.wso2.gw.emulator.core.handlers;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import org.wso2.gw.emulator.core.EmulatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RequestFilterHandler extends ChannelHandlerAdapter {

    private EmulatorContext emulatorContext;
    private Pattern contextPattern;

    public RequestFilterHandler(final EmulatorContext emulatorContext) {
        this.emulatorContext = emulatorContext;
        compilePattern();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            if (!isContextMatching(request.uri())) {
                emulatorContext.getHttpTransportInformation().getIncoming().setContextMatching(false);
            }
        } else {
            // need to handle
        }
    }

    private boolean isContextMatching(String requestContext) {
        Matcher matcher = contextPattern.matcher(requestContext);
        return matcher.find();
    }

    private void compilePattern() {
        this.contextPattern = Pattern.compile("^" + emulatorContext.getHttpTransportInformation().getContext());
    }
}
