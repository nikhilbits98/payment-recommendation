package org.nikhil.payment.recommendation.utils;

import lombok.experimental.UtilityClass;
import org.nikhil.payment.recommendation.constants.LineOfBusiness;
import org.nikhil.payment.recommendation.constants.PaymentInstrumentType;
import org.nikhil.payment.recommendation.enums.PaymentBusiness;
import org.nikhil.payment.recommendation.exceptions.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class PaymentBusinessUtils {

    public static List<PaymentBusiness> getAllPaymentBusinessByLineOfBusiness(LineOfBusiness lineOfBusiness){
        return Arrays.stream(PaymentBusiness.values())
                .filter(paymentBusiness -> paymentBusiness.getLineOfBusiness() == lineOfBusiness)
                .collect(Collectors.toList());
    }

    public static Map<PaymentInstrumentType, PaymentBusiness> getPaymentBusinessMapByLineOfBusiness(LineOfBusiness lineOfBusiness){
        return Arrays.stream(PaymentBusiness.values())
                .filter(paymentBusiness -> paymentBusiness.getLineOfBusiness() == lineOfBusiness)
                .collect(Collectors.toMap(PaymentBusiness::getPaymentInstrumentType, Function.identity()));
    }

    public static PaymentBusiness getPaymentBusinessByLineOfBusinessAndPaymentInstrument(LineOfBusiness lineOfBusiness, PaymentInstrumentType paymentInstrumentType){
        return Arrays.stream(PaymentBusiness.values())
                .filter(paymentBusiness -> paymentBusiness.getLineOfBusiness() == lineOfBusiness)
                .filter(paymentBusiness -> paymentBusiness.getPaymentInstrumentType() == paymentInstrumentType)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No Payment Business found for Line of Business: " + lineOfBusiness + " and Payment Instrument Type: " + paymentInstrumentType));
    }

    public static boolean isPaymentInstrumentAllowedByLineOfBusinessAndCartAmount(PaymentInstrumentType paymentInstrumentType, Double totalCartAmount, LineOfBusiness lineOfBusiness){
        PaymentBusiness paymentBusiness = getPaymentBusinessByLineOfBusinessAndPaymentInstrument(lineOfBusiness, paymentInstrumentType);
        return Double.compare(paymentBusiness.getTransactionLimit(),totalCartAmount) >= 0;
    }
}
