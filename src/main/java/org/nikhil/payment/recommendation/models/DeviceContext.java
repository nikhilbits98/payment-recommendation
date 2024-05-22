package org.nikhil.payment.recommendation.models;

import lombok.Getter;
import org.nikhil.payment.recommendation.constants.DeviceType;

@Getter
public class DeviceContext {
    private final DeviceType deviceType;
    private final boolean isUpiEnabled;

    public DeviceContext(DeviceType deviceType, boolean isUpiEnabled) {
        this.deviceType = deviceType;
        this.isUpiEnabled = isUpiEnabled;
    }
}
