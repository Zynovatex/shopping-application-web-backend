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


    //Cart and Order
    @PostMapping("/cart/add")
    @PreAuthorize("hasAuthority('ROLE_BUYER')")  // ✅ Only BUYERS can add to cart
    public Cart addToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartItem cartItem) {
        if (userDetails == null) {
            throw new RuntimeException("User not authenticated");
        }
        return cartService.addToCart(userDetails.getUsername(), cartItem);
    }
}

