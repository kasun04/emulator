package org.wso2.gw.emulator.core.handlers;

import io.netty.channel.ChannelHandlerAdapter;
import org.wso2.gw.emulator.core.EmulatorContext;

public abstract class AbstractLogicHandler extends ChannelHandlerAdapter {

    protected EmulatorContext emulatorContext;

    public AbstractLogicHandler(EmulatorContext emulatorContext) {
        this.emulatorContext = emulatorContext;
    }
}
