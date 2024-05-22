package org.nikhil.payment.recommendation.services;

import lombok.NonNull;
import org.nikhil.payment.recommendation.constants.DeviceType;
import org.nikhil.payment.recommendation.constants.PaymentInstrumentType;
import org.nikhil.payment.recommendation.constants.PaymentIssuer;
import org.nikhil.payment.recommendation.exceptions.NotFoundException;
import org.nikhil.payment.recommendation.models.DeviceContext;
import org.nikhil.payment.recommendation.models.PaymentInstrument;
import org.nikhil.payment.recommendation.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {

    private static Map<String, User> users;

    public UserService() {
        users = new HashMap<>();
    }

    public User getUserById(@NonNull String userId){
        return Optional.ofNullable(users.get(userId)).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User createUser(@NonNull String name){
        User user = new User(name);
        users.put(user.getId(), user);
        return user;
    }

    public void logInFromDevice(@NonNull String userId, @NonNull DeviceType deviceType, boolean isUpiAllowed){
        DeviceContext deviceContext = new DeviceContext(deviceType, isUpiAllowed);
        User user = getUserById(userId);
        user.setDeviceContext(deviceContext);
    }

    public PaymentInstrument addPaymentInstrumentForUser(@NonNull String userId, PaymentInstrumentType paymentInstrumentType, PaymentIssuer issuer, Double relevanceScore){
        User user = getUserById(userId);
        PaymentInstrument paymentInstrument = new PaymentInstrument(paymentInstrumentType, issuer, relevanceScore, user);
        user.addPaymentInstrument(paymentInstrument);
        return paymentInstrument;
    }
}
