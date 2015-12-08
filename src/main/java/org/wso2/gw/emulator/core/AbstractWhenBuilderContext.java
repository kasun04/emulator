package org.wso2.gw.emulator.core;

/**
 * Created by chamile on 12/7/15.
 */
public abstract class AbstractWhenBuilderContext<T extends AbstractRequestBuilderContext>{

    public abstract AbstractThenBuilderContext when(T requestContextBuilder);
}
