package com.example.virtual_city.controller;

import com.example.virtual_city.dto.ProductResponseDTO;
import com.example.virtual_city.dto.ShopResponseDTO;

import com.example.virtual_city.dto.ShopWithProductsResponse;
import com.example.virtual_city.model.Product;
import com.example.virtual_city.model.Shop;
import com.example.virtual_city.service.ProductService;
import com.example.virtual_city.service.ShopService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shops")
public class ShopController {
    private final ShopService shopService;
    private final ProductService productService;

    public ShopController(ShopService shopService, ProductService productService) {
        this.shopService = shopService;
        this.productService = productService;
    }

    /**
     * GET /api/shops
     * Returns a list of all approved shops with basic info.
     */
    @GetMapping
    public List<ShopResponseDTO> listShops() {
        return shopService.getAllApprovedShops()
                .stream()
                .map(ShopResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * GET /api/shops/{id}
     * Returns full shop details plus its products.
     */
    @GetMapping("/{id}")
    public ShopWithProductsResponse getShop(@PathVariable Long id) {
        Shop shop = shopService.getApprovedShop(id);
        List<Product> products = productService.getProductsForShop(id);

        return ShopWithProductsResponse.from(shop, products);
    }
}
