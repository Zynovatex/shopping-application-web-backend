package com.example.virtual_city.controller;

import com.example.virtual_city.dto.ShopListResponseDTO;
import com.example.virtual_city.dto.ShopResponseDTO;
import com.example.virtual_city.service.ShopService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/list")
    public List<ShopListResponseDTO> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/{id}")
    public ShopResponseDTO getShopWithProducts(@PathVariable Long id) {
        return shopService.getShopWithProducts(id);
    }

}
