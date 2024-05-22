package org.nikhil.payment.recommendation.services;

import lombok.NonNull;
import org.nikhil.payment.recommendation.constants.LineOfBusiness;
import org.nikhil.payment.recommendation.exceptions.NotFoundException;
import org.nikhil.payment.recommendation.models.Cart;
import org.nikhil.payment.recommendation.models.CartItem;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private static Map<String, Cart> carts;

    public CartService() {
        carts = new HashMap<>();
    }

    public Cart createCart(@NonNull LineOfBusiness lineOfBusiness){
        Cart cart = new Cart(lineOfBusiness);
        carts.put(cart.getId(), cart);
        return cart;
    }

    public Cart getCartById(@NonNull String id){
        return carts.get(id);
    }

    public CartItem addCartItem(@NonNull String cartId, @NonNull String itemName, @NonNull Double amount){
        Cart cart = carts.get(cartId);
        CartItem cartItem = new CartItem(itemName, amount);
        cart.addCartItem(cartItem);
        return cartItem;
    }

    public void removeCartItem(@NonNull String cartId, @NonNull String cartItemId){
        Cart cart = carts.get(cartId);
        CartItem cartItem = cart.getCartItems().stream().filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cart item not found"));
        cart.removeCartItem(cartItem);
    }
}
