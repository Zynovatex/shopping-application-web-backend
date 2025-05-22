package com.example.virtual_city.controller;

import com.example.virtual_city.model.Cart;
import com.example.virtual_city.model.CartItem;
import com.example.virtual_city.service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {

    private final CartService cartService;

    public BuyerController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    public String getBuyerDashboard() {
        return "Buyer Dashboard - Access Restricted to Buyers";
    }

    // Add item to cart
    @PostMapping("/cart/add")
    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    public Cart addToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartItem cartItem) {
        if (userDetails == null) {
            throw new RuntimeException("User not authenticated");
        }
        return cartService.addToCart(userDetails.getUsername(), cartItem);
    }

    // Get cart
    @GetMapping("/cart")
    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    public Cart getCart(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new RuntimeException("User not authenticated");
        }
        return cartService.getCartForBuyer(userDetails.getUsername());
    }

    // Remove item from cart
    @DeleteMapping("/cart/remove")
    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    public Cart removeFromCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long productId) {
        if (userDetails == null) {
            throw new RuntimeException("User not authenticated");
        }
        return cartService.removeItemFromCart(userDetails.getUsername(), productId);
    }

    // Update cart quantity
    @PutMapping("/cart/update")
    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    public Cart updateCartQuantity(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        if (userDetails == null) {
            throw new RuntimeException("User not authenticated");
        }
        return cartService.updateCartQuantity(userDetails.getUsername(), productId, quantity);
    }
}
