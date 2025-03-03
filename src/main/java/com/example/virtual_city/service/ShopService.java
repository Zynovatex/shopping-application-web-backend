package com.example.virtual_city.service;

import com.example.virtual_city.dto.ProductResponseDTO;
import com.example.virtual_city.dto.ShopListResponseDTO;
import com.example.virtual_city.dto.ShopResponseDTO;
import com.example.virtual_city.model.Product;
import com.example.virtual_city.model.Shop;
import com.example.virtual_city.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<ShopListResponseDTO> getAllShops() {
        return shopRepository.findAllWithRatings();
    }

    public ShopResponseDTO getShopWithProducts(Long id) {
        return shopRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    private ShopResponseDTO mapToDTO(Shop shop) {
        return ShopResponseDTO.builder()
                .id(shop.getId())
                .name(shop.getName())
                .category(shop.getCategory())
                .isClosed(shop.isClosed())
                .type(shop.getType())
                .offersDelivery(shop.isOffersDelivery())
                .offersTakeaway(shop.isOffersTakeaway())
                .status(shop.getStatus())
                .imageUrl(shop.getImageUrl())
                .products(shop.getProducts().stream().map(this::mapToProductDTO).toList())
                .build();
    }

    private ProductResponseDTO mapToProductDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
