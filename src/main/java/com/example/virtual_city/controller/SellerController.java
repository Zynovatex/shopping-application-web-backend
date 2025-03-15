package com.example.virtual_city.controller;

import com.example.virtual_city.dto.ShopDTO;
import com.example.virtual_city.model.Shop;
import com.example.virtual_city.service.ShopService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller/shop")
public class SellerController {
    private final ShopService shopService;

    public SellerController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/register")
    public Shop registerShop(@AuthenticationPrincipal(errorOnInvalidType=true) UserDetails userDetails, @RequestBody ShopDTO shopDTO) {
        if (userDetails == null) {
            throw new RuntimeException("User is not authenticated. Please login.");
        }
        return shopService.registerShop(userDetails.getUsername(), shopDTO);
    }

}
