package com.example.virtual_city.repository;

import com.example.virtual_city.model.Product;
import com.example.virtual_city.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(User seller);
}
