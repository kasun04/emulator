package org.wso2.gw.emulator.core.handlers;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.wso2.gw.emulator.core.EmulatorContext;

public class ReadingDelayHandler extends ChannelHandlerAdapter {

    private final EmulatorContext emulatorContext;

    public ReadingDelayHandler(final EmulatorContext emulatorContext) {
        this.emulatorContext = emulatorContext;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (emulatorContext.getHttpTransportInformation() != null && emulatorContext.getHttpTransportInformation()
                                                                             .getReadingDelay() > 0) {
            delay(emulatorContext.getHttpTransportInformation().getReadingDelay());
        }
    }

    private void delay(int readingDelay) {
        try {
            Thread.sleep(readingDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();//need to handle
        }
    }
}
