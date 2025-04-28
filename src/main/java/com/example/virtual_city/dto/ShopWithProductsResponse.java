
package com.example.virtual_city.dto;

import com.example.virtual_city.model.Product;
import com.example.virtual_city.model.Shop;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShopWithProductsResponse {
    private ShopResponseDTO shop;
    private List<ProductResponseDTO> products;

    public ShopResponseDTO getShop() {
        return shop;
    }

    public void setShop(ShopResponseDTO shop) {
        this.shop = shop;
    }

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }

    public static ShopWithProductsResponse from(Shop shopEntity, List<Product> products) {
        ShopWithProductsResponse dto = new ShopWithProductsResponse();
        dto.setShop(ShopResponseDTO.fromEntity(shopEntity));
        dto.setProducts(
                products.stream()
                        .map(ProductResponseDTO::fromEntity)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}