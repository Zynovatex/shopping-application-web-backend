package com.example.virtual_city.service;

import com.example.virtual_city.dto.ShopResponseDTO;
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

    public List<ShopResponseDTO> getAllShops() {
        return shopRepository.findAllWithRatings();
    }

}
