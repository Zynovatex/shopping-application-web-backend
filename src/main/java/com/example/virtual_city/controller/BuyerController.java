package com.example.virtual_city.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    public String getBuyerDashboard() {
        return "Buyer Dashboard - Access Restricted to Buyers";
    }
}
