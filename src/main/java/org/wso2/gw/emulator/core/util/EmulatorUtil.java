package org.wso2.gw.emulator.core.util;

import org.wso2.gw.emulator.http.dsl.dto.Outgoing;
import org.wso2.gw.emulator.http.dsl.dto.HttpTransportInformation;

import java.util.Collections;


public class EmulatorUtil {

    public static byte[] getResponseContent(HttpTransportInformation transportInformation) {
        if (transportInformation == null || transportInformation.getOutgoing() == null || transportInformation.getOutgoing().getBody() == null ||
            transportInformation.getOutgoing().getBody().isEmpty
                    ()) {
            return new byte[0];
        }
        return transportInformation.getOutgoing().getBody().getBytes();
    }

}
