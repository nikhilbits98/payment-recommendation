package org.nikhil.payment.recommendation.models;

import lombok.Getter;
import org.nikhil.payment.recommendation.constants.LineOfBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Cart {
    private final String id;
    private final LineOfBusiness lineOfBusiness;
    private final List<CartItem> cartItems;
    private Double totalAmount;

    public Cart(LineOfBusiness lineOfBusiness) {
        this.id = UUID.randomUUID().toString();
        this.lineOfBusiness = lineOfBusiness;
        this.cartItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addCartItem(CartItem cartItem){
        this.cartItems.add(cartItem);
        this.totalAmount += cartItem.getAmount();
    }

    public void removeCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
        this.totalAmount -= cartItem.getAmount();
    }
}
