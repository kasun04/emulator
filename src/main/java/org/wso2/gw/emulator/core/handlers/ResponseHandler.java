package org.wso2.gw.emulator.core.handlers;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.SET_COOKIE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.ServerCookieEncoder;
import io.netty.util.CharsetUtil;
import org.wso2.gw.emulator.core.EmulatorContext;

import java.util.Map;

public class ResponseHandler extends ChannelHandlerAdapter {

    private final EmulatorContext emulatorContext;

    public ResponseHandler(final EmulatorContext emulatorContext) {
        this.emulatorContext = emulatorContext;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;

            if (HttpHeaderUtil.is100ContinueExpected(request)) {
                send100Continue(ctx);
            }

            if (!emulatorContext.getHttpTransportInformation().getIncoming().isContextMatching() || isResourceFound
                    (request)) {
                if (!write404NotFoundResponse(request, ctx)) {
                    // If keep-alive is off, close the connection once the content is fully written.
                    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                }
            } else {
                if (isRequestContentFound()) {
                    if (!writeResponse(request, ctx)) {
                        // If keep-alive is off, close the connection once the content is fully written.
                        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                    }
                } else {
                    if (!write400BadResponse(request, ctx)) {
                        // If keep-alive is off, close the connection once the content is fully written.
                        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                    }
                }
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    private boolean writeResponse(HttpRequest request, ChannelHandlerContext ctx) {
        // Decide whether to close the connection or not.
        boolean keepAlive = HttpHeaderUtil.isKeepAlive(request);

        HttpVersion httpVersion = emulatorContext.getHttpTransportInformation().getHttpVersion();
        HttpResponseStatus httpResponseStatus = emulatorContext.getHttpTransportInformation().getOutgoing().getStatusCode();
        String content = emulatorContext.getHttpTransportInformation().getOutgoing().getBody();
        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(
                httpVersion, httpResponseStatus,
                Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));

        populateHttpHeaders(response);
        populateCookies(response);

        if (!keepAlive) {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.write(response);
        }

        return keepAlive;
    }

    private boolean write404NotFoundResponse(HttpRequest request, ChannelHandlerContext ctx) {

        // Decide whether to close the connection or not.
        boolean keepAlive = HttpHeaderUtil.isKeepAlive(request);

        HttpVersion httpVersion = emulatorContext.getHttpTransportInformation().getHttpVersion();
        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(
                httpVersion, NOT_FOUND);

        if (!keepAlive) {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.write(response);
        }
        return keepAlive;
    }

    private boolean write400BadResponse(HttpRequest request, ChannelHandlerContext ctx) {

        // Decide whether to close the connection or not.
        boolean keepAlive = HttpHeaderUtil.isKeepAlive(request);

        HttpVersion httpVersion = emulatorContext.getHttpTransportInformation().getHttpVersion();
        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(
                httpVersion, BAD_REQUEST);

        if (!keepAlive) {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.write(response);
        }
        return keepAlive;
    }

    private boolean isResourceFound(HttpRequest request) {
        String expectedPath = emulatorContext.getHttpTransportInformation().getIncoming().getPath();
        HttpMethod expectedMethod = emulatorContext.getHttpTransportInformation().getIncoming().getMethod();
        //Need to implement
        return true;
    }

    private boolean isRequestContentFound() {
        return true; //need to implement
    }

    private void populateHttpHeaders(FullHttpResponse response) {

        for (Map.Entry<String, String> entry : emulatorContext.getHttpTransportInformation().getOutgoing().getHeaders
                ().entrySet()) {
            response.headers().add(entry.getKey(), entry.getValue());
        }
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
    }

    private void populateCookies(FullHttpResponse response) {

        for (Map.Entry<String, String> entry : emulatorContext.getHttpTransportInformation().getOutgoing().getCookies
                ().entrySet()) {
            response.headers().add(SET_COOKIE, ServerCookieEncoder.encode(entry.getKey(), entry.getValue()));
        }
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, CONTINUE);
        ctx.write(response);
    }

}
