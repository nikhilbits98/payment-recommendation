package org.nikhil.payment.recommendation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nikhil.payment.recommendation.constants.DeviceType;
import org.nikhil.payment.recommendation.constants.LineOfBusiness;
import org.nikhil.payment.recommendation.constants.PaymentInstrumentType;
import org.nikhil.payment.recommendation.constants.PaymentIssuer;
import org.nikhil.payment.recommendation.facade.PaymentRecommendationFacade;
import org.nikhil.payment.recommendation.models.Cart;
import org.nikhil.payment.recommendation.models.PaymentInstrument;
import org.nikhil.payment.recommendation.models.User;
import org.nikhil.payment.recommendation.services.CartService;
import org.nikhil.payment.recommendation.services.UserService;

import java.util.List;

class PaymentRecommendationTest {

    private PaymentRecommendationFacade paymentRecommendationFacade;
    private UserService userService;
    private CartService cartService;

    @BeforeEach
    public void setUp(){
        userService = new UserService();
        cartService = new CartService();
        paymentRecommendationFacade = new PaymentRecommendationFacade(userService, cartService);
    }

    @Test
    public void testCreditCardLineOfBusiness(){
        String userId = setupUser();
        System.out.println("Recommendation for Credit Card");
        Cart cart = cartService.createCart(LineOfBusiness.CREDIT_CARD_PAYMENT);
        cartService.addCartItem(cart.getId(), "Item1", 100000.0);
        List<PaymentInstrument> paymentInstrumentList = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList);
        cartService.addCartItem(cart.getId(), "Item2", 50000.0);
        List<PaymentInstrument> paymentInstrumentList2 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList2);
        cartService.addCartItem(cart.getId(), "Item3", 50000.0);
        List<PaymentInstrument> paymentInstrumentList3 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList3);
        cartService.addCartItem(cart.getId(), "Item3", 200000.0);
        List<PaymentInstrument> paymentInstrumentList4 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList4);
    }

    @Test
    public void testECommerceLineOfBusiness(){
        String userId = setupUser();
        System.out.println("Recommendation for E Commerce");
        Cart cart = cartService.createCart(LineOfBusiness.E_COMMERCE);
        cartService.addCartItem(cart.getId(), "Item1", 100000.0);
        List<PaymentInstrument> paymentInstrumentList = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList);
        cartService.addCartItem(cart.getId(), "Item2", 50000.0);
        List<PaymentInstrument> paymentInstrumentList2 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList2);
        cartService.addCartItem(cart.getId(), "Item3", 50000.0);
        List<PaymentInstrument> paymentInstrumentList3 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList3);
        cartService.addCartItem(cart.getId(), "Item3", 200000.0);
        List<PaymentInstrument> paymentInstrumentList4 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList4);
    }

    @Test
    public void testInvestmentLineOfBusiness(){
        String userId = setupUser();
        System.out.println("Recommendation for Investment");
        Cart cart = cartService.createCart(LineOfBusiness.INVESTMENT);
        cartService.addCartItem(cart.getId(), "Item1", 100000.0);
        List<PaymentInstrument> paymentInstrumentList = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList);
        cartService.addCartItem(cart.getId(), "Item2", 50000.0);
        List<PaymentInstrument> paymentInstrumentList2 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList2);
        cartService.addCartItem(cart.getId(), "Item3", 50000.0);
        List<PaymentInstrument> paymentInstrumentList3 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList3);
        cartService.addCartItem(cart.getId(), "Item3", 200000.0);
        List<PaymentInstrument> paymentInstrumentList4 = paymentRecommendationFacade.recommendPayments(userId, cart.getId());
        System.out.println("CartAmount:" + cart.getTotalAmount() + " Recommendation:" + paymentInstrumentList4);
    }

    private String setupUser(){
        User user = userService.createUser("Nikhil");
        String userId = user.getId();
        userService.logInFromDevice(userId, DeviceType.MOBILE, true);
        userService.addPaymentInstrumentForUser(userId, PaymentInstrumentType.CREDIT_CARD, PaymentIssuer.AMEX, 0.8);
        userService.addPaymentInstrumentForUser(userId, PaymentInstrumentType.CREDIT_CARD, PaymentIssuer.HDFC, 0.7);
        userService.addPaymentInstrumentForUser(userId, PaymentInstrumentType.UPI, PaymentIssuer.HDFC, 0.9);
        userService.addPaymentInstrumentForUser(userId, PaymentInstrumentType.UPI, PaymentIssuer.SBI, 0.6);
        userService.addPaymentInstrumentForUser(userId, PaymentInstrumentType.DEBIT_CARD, PaymentIssuer.ICICI, 0.2);
        userService.addPaymentInstrumentForUser(userId, PaymentInstrumentType.DEBIT_CARD, PaymentIssuer.SBI, 1.0);
        userService.addPaymentInstrumentForUser(userId, PaymentInstrumentType.NET_BANKING, PaymentIssuer.AMEX, 0.5);
        userService.addPaymentInstrumentForUser(userId, PaymentInstrumentType.NET_BANKING, PaymentIssuer.AXIS, 0.3);
        return userId;
    }

}