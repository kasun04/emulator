package org.wso2.gw.emulator.core.handlers;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.wso2.gw.emulator.core.EmulatorContext;

import java.util.List;
import java.util.Map;

public class RequestInfoPopulateHandler extends ChannelHandlerAdapter {

    private EmulatorContext emulatorContext;

    public RequestInfoPopulateHandler(final EmulatorContext emulatorContext) {
        this.emulatorContext = emulatorContext;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            populateRequestHeaders(request);
            populateQueryParameters(request);
            populateRequestContent(request);
        }
    }

    private void populateRequestHeaders(HttpRequest request) {
        HttpHeaders headers = request.headers();
        if (!headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entriesConverted()) {
                emulatorContext.getHttpTransportInformation().getIncoming().addHeader(entry.getKey
                        (), entry.getValue());
            }
        }
    }

    private void populateQueryParameters(HttpRequest request) {

        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
        Map<String, List<String>> params = queryStringDecoder.parameters();
        if (!params.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : params.entrySet()) {
                for (String value : entry.getValue()) {
                    emulatorContext.getHttpTransportInformation().getIncoming()
                            .addQueryParameter(entry.getKey(), value);
                }
            }
        }
    }

    private void populateRequestContent(HttpRequest request) {
        emulatorContext.getHttpTransportInformation().getIncoming().setRequestBody(request.decoderResult());
    }
}