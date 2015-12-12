package org.wso2.gw.emulator.core.contexts;

/**
 * Created by chamile on 12/7/15.
 */
public abstract class AbstractConfigurationBuilderContext {

    public abstract AbstractConfigurationBuilderContext host(String host);

    public abstract AbstractConfigurationBuilderContext port(int port);

}