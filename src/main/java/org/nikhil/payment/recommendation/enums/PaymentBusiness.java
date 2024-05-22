package org.nikhil.payment.recommendation.enums;

import lombok.Getter;
import org.nikhil.payment.recommendation.constants.LineOfBusiness;
import org.nikhil.payment.recommendation.constants.PaymentInstrumentType;

@Getter
public enum PaymentBusiness {
    CREDIT_CARD_PAYMENT_WITH_CREDIT_CARD(LineOfBusiness.CREDIT_CARD_PAYMENT, PaymentInstrumentType.CREDIT_CARD, false, null, null),
    CREDIT_CARD_PAYMENT_WITH_DEBIT_CARD(LineOfBusiness.CREDIT_CARD_PAYMENT, PaymentInstrumentType.DEBIT_CARD, true, 200000.0,3),
    CREDIT_CARD_PAYMENT_WITH_UPI(LineOfBusiness.CREDIT_CARD_PAYMENT, PaymentInstrumentType.UPI, true, 200000.0, 1),
    CREDIT_CARD_PAYMENT_WITH_NET_BANKING(LineOfBusiness.CREDIT_CARD_PAYMENT, PaymentInstrumentType.NET_BANKING, true, 200000.0, 2),
    E_COMMERCE_PAYMENT_WITH_CREDIT_CARD(LineOfBusiness.E_COMMERCE, PaymentInstrumentType.CREDIT_CARD, true, 250000.0, 1),
    E_COMMERCE_PAYMENT_WITH_DEBIT_CARD(LineOfBusiness.E_COMMERCE, PaymentInstrumentType.DEBIT_CARD, true, 200000.0, 3),
    E_COMMERCE_PAYMENT_WITH_UPI(LineOfBusiness.E_COMMERCE, PaymentInstrumentType.UPI, true, 100000.0, 2),
    E_COMMERCE_PAYMENT_WITH_NET_BANKING(LineOfBusiness.E_COMMERCE, PaymentInstrumentType.NET_BANKING, false, null, null),
    INVESTMENT_PAYMENT_WITH_CREDIT_CARD(LineOfBusiness.INVESTMENT, PaymentInstrumentType.CREDIT_CARD, false, null, null),
    INVESTMENT_PAYMENT_WITH_DEBIT_CARD(LineOfBusiness.INVESTMENT, PaymentInstrumentType.DEBIT_CARD, true, 150000.0, 3),
    INVESTMENT_PAYMENT_WITH_UPI(LineOfBusiness.INVESTMENT, PaymentInstrumentType.UPI, true, 100000.0, 1),
    INVESTMENT_PAYMENT_WITH_NET_BANKING(LineOfBusiness.INVESTMENT, PaymentInstrumentType.NET_BANKING, true, 150000.0, 2);

    private final LineOfBusiness lineOfBusiness;
    private final PaymentInstrumentType paymentInstrumentType;
    private final boolean isAllowed;
    private final Double transactionLimit;
    private final Integer priority; // 1 is highest priority

    PaymentBusiness(LineOfBusiness lineOfBusiness,
                    PaymentInstrumentType paymentInstrumentType,
                    boolean isAllowed,
                    Double transactionLimit,
                    Integer priority) {
        this.lineOfBusiness = lineOfBusiness;
        this.paymentInstrumentType = paymentInstrumentType;
        this.isAllowed = isAllowed;
        this.transactionLimit = transactionLimit;
        this.priority = priority;
    }
}
