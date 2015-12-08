package org.wso2.gw.emulator.core;

import org.wso2.gw.emulator.http.server.HttpServerGivenBuilderContext;

/**
 * Created by chamile on 12/7/15.
 */
public abstract class AbstractGivenBuilderContext<T extends AbstractConfigurationBuilderContext>{

    public abstract HttpServerGivenBuilderContext given(T configurationContext);
}