package org.wso2.gw.emulator.core.contexts;

public abstract class AbstractServerOperationBuilderContext extends AbstractOperationBuilderContext {

    public abstract AbstractServerOperationBuilderContext start();

    public abstract AbstractServerOperationBuilderContext stop();
}
