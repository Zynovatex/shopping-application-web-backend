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

    public Cart getCartForBuyer(String buyerEmail) {
        return cartRepository.findCartByBuyer(buyerEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }

    public Cart removeItemFromCart(String buyerEmail, Long productId) {
        Cart cart = cartRepository.findCartByBuyer(buyerEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        if (!cart.getItems().containsKey(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in cart");
        }

        cart.getItems().remove(productId);
        cartRepository.saveCart(buyerEmail, cart);  // ✅ Save updated cart after removal
        return cart;
    }

    public Cart updateCartQuantity(String buyerEmail, Long productId, int quantity) {
        Cart cart = cartRepository.findCartByBuyer(buyerEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        CartItem cartItem = cart.getItems().get(productId);
        if (cartItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in cart");
        }

        cartItem.setQuantity(quantity); // ✅ Update the quantity
        cartRepository.saveCart(buyerEmail, cart); // ✅ Save updated cart
        return cart;
    }



}

