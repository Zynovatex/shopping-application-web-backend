package com.example.virtual_city.controller;

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
    public List<ShopResponseDTO> getAllShops() {
        return shopService.getAllShops();
    }



}
