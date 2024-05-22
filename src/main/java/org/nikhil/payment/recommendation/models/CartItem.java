package org.nikhil.payment.recommendation.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CartItem {
    private final String id;
    private final String itemName;
    private final Double amount;

    public CartItem(String itemName, Double amount) {
        this.id = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.amount = amount;
    }
}
