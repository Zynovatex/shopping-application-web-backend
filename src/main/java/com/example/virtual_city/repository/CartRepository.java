package com.example.virtual_city.repository;


import com.example.virtual_city.model.Cart;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartRepository {
    private final RedisTemplate<String, Cart> redisTemplate;

    public CartRepository(RedisTemplate<String, Cart> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveCart(String buyerEmail, Cart cart) {
        redisTemplate.opsForValue().set("cart:" + buyerEmail, cart);
    }

    public Optional<Cart> findCartByBuyer(String buyerEmail) {
        return Optional.ofNullable(redisTemplate.opsForValue().get("cart:" + buyerEmail));
    }

    public void deleteCart(String buyerEmail) {
        redisTemplate.delete("cart:" + buyerEmail);
    }
}
