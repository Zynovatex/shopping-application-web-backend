
package com.example.virtual_city.dto;

import com.example.virtual_city.model.Shop;
import lombok.Data;
import java.util.List;

@Data
public class ShopResponseDTO {
    private Long id;
    private String shopName;
    private String address;
    private String category;
    private List<String> shopImages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getShopImages() {
        return shopImages;
    }

    public void setShopImages(List<String> shopImages) {
        this.shopImages = shopImages;
    }

    public static ShopResponseDTO fromEntity(Shop shop) {
        ShopResponseDTO dto = new ShopResponseDTO();
        dto.setId(shop.getId());
        dto.setShopName(shop.getShopName());
        dto.setAddress(shop.getAddress());
        dto.setCategory(shop.getCategory());
        dto.setShopImages(shop.getShopImages());
        return dto;
    }
}
