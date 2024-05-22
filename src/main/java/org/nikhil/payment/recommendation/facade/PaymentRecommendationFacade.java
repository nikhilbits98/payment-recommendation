package org.nikhil.payment.recommendation.facade;

import lombok.NonNull;
import org.nikhil.payment.recommendation.constants.PaymentInstrumentType;
import org.nikhil.payment.recommendation.enums.PaymentBusiness;
import org.nikhil.payment.recommendation.models.Cart;
import org.nikhil.payment.recommendation.models.PaymentInstrument;
import org.nikhil.payment.recommendation.models.User;
import org.nikhil.payment.recommendation.services.CartService;
import org.nikhil.payment.recommendation.services.UserService;
import org.nikhil.payment.recommendation.utils.PaymentBusinessUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentRecommendationFacade {

    private final UserService userService;
    private final CartService cartService;

    public PaymentRecommendationFacade(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    public List<PaymentInstrument> recommendPayments(@NonNull String userId, @NonNull String cartId) {
        User user = userService.getUserById(userId);
        Cart cart = cartService.getCartById(cartId);
        Map<PaymentInstrumentType, PaymentBusiness> paymentBusinessConfig =
                PaymentBusinessUtils.getPaymentBusinessMapByLineOfBusiness(cart.getLineOfBusiness());
        List<PaymentInstrument> allowedPaymentInstruments = getPaymentInstrumentsAllowedByConfig(user, paymentBusinessConfig);
        allowedPaymentInstruments = filterPaymentInstrumentsByDevice(user, allowedPaymentInstruments);
        allowedPaymentInstruments = filterPaymentInstrumentsByCart(cart, allowedPaymentInstruments);
        return sortPaymentInstrumentsByPriorityAndRelevanceScore(allowedPaymentInstruments, paymentBusinessConfig);
    }

    public List<PaymentInstrument> getPaymentInstrumentsAllowedByConfig(User user, Map<PaymentInstrumentType, PaymentBusiness> paymentBusinessConfig) {
        List<PaymentInstrument> allPaymentInstruments = user.getPaymentInstruments();
        return allPaymentInstruments.stream()
                .filter(paymentInstrument -> paymentBusinessConfig.containsKey(paymentInstrument.getPaymentInstrumentType()))
                .filter(paymentInstrument -> paymentBusinessConfig.get(paymentInstrument.getPaymentInstrumentType()).isAllowed())
                .collect(Collectors.toList());
    }

    public List<PaymentInstrument> filterPaymentInstrumentsByDevice(User user, List<PaymentInstrument> paymentInstruments) {
        boolean isUpiAllowed = user.getDeviceContext() != null && user.getDeviceContext().isUpiEnabled();
        if (!isUpiAllowed) {
            paymentInstruments = paymentInstruments.stream()
                    .filter(paymentInstrument -> paymentInstrument.getPaymentInstrumentType() != PaymentInstrumentType.UPI)
                    .collect(Collectors.toList());
        }
        return paymentInstruments;
    }

    public List<PaymentInstrument> filterPaymentInstrumentsByCart(Cart cart, List<PaymentInstrument> paymentInstruments) {
        Double totalCartAmount = cart.getTotalAmount();
        return paymentInstruments.stream()
                .filter(paymentInstrument -> PaymentBusinessUtils.isPaymentInstrumentAllowedByLineOfBusinessAndCartAmount(
                        paymentInstrument.getPaymentInstrumentType(), totalCartAmount, cart.getLineOfBusiness()))
                .collect(Collectors.toList());
    }

    public List<PaymentInstrument> sortPaymentInstrumentsByPriorityAndRelevanceScore(List<PaymentInstrument> paymentInstruments, Map<PaymentInstrumentType, PaymentBusiness> paymentBusinessConfig){
        return paymentInstruments.stream()
                .sorted((p1, p2) -> {
                    PaymentBusiness pb1 = paymentBusinessConfig.get(p1.getPaymentInstrumentType());
                    PaymentBusiness pb2 = paymentBusinessConfig.get(p2.getPaymentInstrumentType());
                    if(pb1.getPriority() < pb2.getPriority()){
                        return -1;
                    }else if(pb1.getPriority() > pb2.getPriority()) {
                        return 1;
                    } else {
                        return Double.compare(p2.getRelevanceScore(), p1.getRelevanceScore());
                    }
                }).collect(Collectors.toList());
    }
}
