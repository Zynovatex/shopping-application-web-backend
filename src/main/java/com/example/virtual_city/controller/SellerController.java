package com.example.virtual_city.controller;

import com.example.virtual_city.dto.ProductDTO;
import com.example.virtual_city.dto.ShopDTO;
import com.example.virtual_city.model.Product;
import com.example.virtual_city.model.Shop;
import com.example.virtual_city.service.ProductService;
import com.example.virtual_city.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/seller/shop")
public class SellerController {
    private final ShopService shopService;
    private final ProductService productService;

    public SellerController(ShopService shopService, ProductService productService) {
        this.shopService = shopService;
        this.productService = productService;
    }
    //Business Registration and Authorization

    @PostMapping("/register")
    public Shop registerShop(@AuthenticationPrincipal(errorOnInvalidType=true) UserDetails userDetails, @RequestBody ShopDTO shopDTO) {
        if (userDetails == null) {
            throw new RuntimeException("User is not authenticated. Please login.");
        }
        return shopService.registerShop(userDetails.getUsername(), shopDTO);
    }

    //Product Listing & Inventory
    @PostMapping("/product/add")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")  // ✅ Restrict to sellers only
    public Product addProduct(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ProductDTO productDTO) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        String sellerEmail = userDetails.getUsername();  // ✅ Extract email from logged-in user
        return productService.addProduct(sellerEmail, productDTO);
    }

}
