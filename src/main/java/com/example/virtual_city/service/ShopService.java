package com.example.virtual_city.service;

import com.example.virtual_city.dto.ShopDTO;
import com.example.virtual_city.model.Shop;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.ShopRepository;
import com.example.virtual_city.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    public ShopService(ShopRepository shopRepository, UserRepository userRepository) {
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
    }

    public Shop registerShop(String sellerEmail, ShopDTO shopDTO) {
        // ✅ Validate seller existence
        User seller = userRepository.findByEmail(sellerEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seller not found"));

        // ✅ Validate required fields
        if (shopDTO.getShopName() == null || shopDTO.getShopName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shop name is required.");
        }

        if (shopDTO.getAddress() == null || shopDTO.getAddress().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shop address is required.");
        }

        if (shopDTO.getCategory() == null || shopDTO.getCategory().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shop category is required.");
        }

        if (shopDTO.getDistrict() == null || shopDTO.getDistrict().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "District is required.");
        }

        if (shopDTO.getShopImages() == null || shopDTO.getShopImages().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one shop image is required.");
        }

        // ✅ Create a new shop instance
        Shop shop = new Shop();
        shop.setSeller(seller);
        shop.setShopName(shopDTO.getShopName());
        shop.setAddress(shopDTO.getAddress());
        shop.setCategory(shopDTO.getCategory());
        shop.setDistrict(shopDTO.getDistrict());
        shop.setArea(shopDTO.getArea());
        shop.setShopType(shopDTO.getShopType());
        shop.setDeliveryAvailable(shopDTO.isDeliveryAvailable());
        shop.setDescription(shopDTO.getDescription());
        shop.setShopImages(shopDTO.getShopImages()); // ✅ Store image URLs
        shop.setRegistrationNumber(shopDTO.getRegistrationNumber());
        shop.setTaxNumber(shopDTO.getTaxNumber());
        shop.setVatRegistered(shopDTO.isVatRegistered());
        shop.setRegistrationType(shopDTO.getRegistrationType());
        shop.setRegistrationDocuments(shopDTO.getRegistrationDocuments()); // ✅ Store document URLs
        shop.setCashOnDelivery(shopDTO.isCashOnDelivery());
        shop.setOnlinePayment(shopDTO.isOnlinePayment());
        shop.setMobilePayment(shopDTO.isMobilePayment());

        try {
            return shopRepository.save(shop);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while saving the shop.");
        }
    }
    // ✅ New method: Verify if a seller owns a shop
    public Shop verifySellerOwnsShop(User seller) {
        return shopRepository.findBySeller(seller)
                .stream()
                .findFirst() // ✅ Get the first shop owned by the seller
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seller does not own a shop"));
    }
}
