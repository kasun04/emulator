package org.wso2.gw.emulator.core.handlers;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.wso2.gw.emulator.core.EmulatorContext;

public class WaitingDelayHandler extends ChannelHandlerAdapter {

    private final EmulatorContext emulatorContext;

    public WaitingDelayHandler(final EmulatorContext emulatorContext) {
        this.emulatorContext = emulatorContext;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (emulatorContext.getHttpTransportInformation() != null && emulatorContext.getHttpTransportInformation().getWritingDelay() > 0) {
            delay(emulatorContext.getHttpTransportInformation().getWritingDelay());
        }
    }

    private void delay(int waitingDelay) {
        try {
            Thread.sleep(waitingDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();//need to handle
        }
    }
}
