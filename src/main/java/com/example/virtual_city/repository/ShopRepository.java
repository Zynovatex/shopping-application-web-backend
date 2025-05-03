package com.example.virtual_city.repository;

import com.example.virtual_city.model.Shop;
import com.example.virtual_city.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findBySeller(User seller);
    Optional<Shop> findByIdAndSeller(Long id, User seller);

    // NEW: for public listing of approved shops
    List<Shop> findByApprovedTrue();
    Optional<Shop> findByIdAndApprovedTrue(Long id);
}
