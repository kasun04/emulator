package org.wso2.gw.emulator.core.contexts;

public abstract class AbstractWhenBuilderContext<T extends AbstractRequestBuilderContext>{

    public abstract AbstractThenBuilderContext when(T requestContextBuilder);

    public abstract AbstractOperationBuilderContext operation();
}
