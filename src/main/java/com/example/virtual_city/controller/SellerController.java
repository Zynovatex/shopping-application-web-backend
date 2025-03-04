package com.example.virtual_city.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    public String getSellerDashboard() {
        return "Seller Dashboard - Access Restricted to Sellers";
    }
}
