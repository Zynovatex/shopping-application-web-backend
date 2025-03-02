package com.example.virtual_city.repository;

import com.example.virtual_city.dto.ShopResponseDTO;
import com.example.virtual_city.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query(value = "SELECT s.id, s.name, s.category, " +
            "COALESCE(AVG(r.rating), 0) AS averageRating, " +
            "COUNT(r.id) AS ratingCount, " +
            "s.is_closed, s.type, s.offers_delivery, s.offers_takeaway, s.status, s.image_url " +
            "FROM shops s " +
            "LEFT JOIN shop_ratings r ON s.id = r.shop_id " +
            "GROUP BY s.id, s.name, s.category, s.is_closed, s.type, " +
            "s.offers_delivery, s.offers_takeaway, s.status, s.image_url " +
            "ORDER BY averageRating DESC",
            nativeQuery = true)
    List<ShopResponseDTO> findAllWithRatings();
}
