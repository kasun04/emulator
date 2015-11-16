package org.wso2.gw.emulator.core;

import org.wso2.gw.emulator.http.dsl.dto.HttpTransportInformation;

public class EmulatorContext {

    private HttpTransportInformation httpTransportInformation;

    public HttpTransportInformation getHttpTransportInformation() {
        return httpTransportInformation;
    }

    public void setHttpTransportInformation(HttpTransportInformation httpTransportInformation) {
        this.httpTransportInformation = httpTransportInformation;
    }
}
