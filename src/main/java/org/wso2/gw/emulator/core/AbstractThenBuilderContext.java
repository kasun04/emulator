package org.wso2.gw.emulator.core;

/**
 * Created by chamile on 12/7/15.
 */
public abstract class AbstractThenBuilderContext<T extends AbstractResponseBuilderContext>{

    public abstract AbstractWhenBuilderContext then(T responseBuilderContext);
}

