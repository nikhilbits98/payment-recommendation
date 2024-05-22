package org.nikhil.payment.recommendation.models;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class User {
    private final String id;
    private final String name;
    private DeviceContext deviceContext;
    private List<PaymentInstrument> paymentInstruments;

    public User(@NonNull String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.deviceContext = null;
        this.paymentInstruments = new ArrayList<PaymentInstrument>();
    }

    public void setDeviceContext(@NonNull DeviceContext deviceContext) {
        this.deviceContext = deviceContext;
    }

    public void addPaymentInstrument(@NonNull PaymentInstrument paymentInstrument) {
        this.paymentInstruments.add(paymentInstrument);
    }
}
