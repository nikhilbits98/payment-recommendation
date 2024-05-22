package org.nikhil.payment.recommendation.models;

import lombok.Getter;
import lombok.ToString;
import org.nikhil.payment.recommendation.constants.PaymentInstrumentType;
import org.nikhil.payment.recommendation.constants.PaymentIssuer;

import java.util.UUID;

@Getter
public class PaymentInstrument {
    private final String id;
    private final PaymentInstrumentType paymentInstrumentType;
    private final PaymentIssuer issuer;
    private final Double relevanceScore;
    private final User user;

    public PaymentInstrument(PaymentInstrumentType paymentInstrumentType, PaymentIssuer issuer, Double relevanceScore, User user) {
        this.id = UUID.randomUUID().toString();
        this.paymentInstrumentType = paymentInstrumentType;
        this.issuer = issuer;
        this.relevanceScore = relevanceScore;
        this.user = user;
    }

    @Override
    public String toString() {
        return "PaymentInstrument{ type=" + paymentInstrumentType + ", issuer=" + issuer + ", relevanceScore=" + relevanceScore + " }";
    }
}
