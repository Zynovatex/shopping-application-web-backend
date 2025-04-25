package com.example.virtual_city.model;


import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Map<Long, CartItem> items = new HashMap<>();// ✅ Store items by productId

    public Map<Long, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Long, CartItem> items) {
        this.items = items;
    }
}
