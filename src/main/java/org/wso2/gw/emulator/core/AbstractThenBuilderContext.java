package org.wso2.gw.emulator.core;

import org.wso2.gw.emulator.http.server.HttpServerWhenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public abstract class AbstractThenBuilderContext<T extends AbstractResponseBuilderContext> {

    public abstract AbstractWhenBuilderContext then(T responseContext);

    public abstract void operation(OperationType operationType);

}
