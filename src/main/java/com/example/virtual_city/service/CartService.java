package com.example.virtual_city.service;


import com.example.virtual_city.model.Cart;
import com.example.virtual_city.model.CartItem;
import com.example.virtual_city.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addToCart(String buyerEmail, CartItem cartItem) {
        Cart cart = cartRepository.findCartByBuyer(buyerEmail).orElse(new Cart());
        cart.getItems().put(cartItem.getProductId(), cartItem);
        cartRepository.saveCart(buyerEmail, cart);
        return cart;
    }
}
