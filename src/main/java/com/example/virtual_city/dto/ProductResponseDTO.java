package com.example.virtual_city.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.virtual_city.model.Product;
import lombok.Data;

public class ProductResponseDTO {
    Long id;
    String productName;
    String description;
    int quantity;
    String brand;
    List<String> images;
    List<String> categories;
    double price;
    double discountPrice;
    String ingredients;
    LocalDate expireDate;
    double taxPrice;
    String seoTitle;
    String seoDescription;
    List<String> tags;
    List<String> sizes;
    double weight;
    double height;
    double volume;
    List<String> shippingCountries;
    int estimatedDeliveryTime;
    double shippingCost;
    private String imageUrl;

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public int getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(int estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public List<String> getShippingCountries() {
        return shippingCountries;
    }

    public void setShippingCountries(List<String> shippingCountries) {
        this.shippingCountries = shippingCountries;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static ProductResponseDTO fromEntity(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImages(product.getImages());
        dto.setCategories(product.getCategories());
        dto.setDiscountPrice(product.getDiscountPrice());
        dto.setQuantity(product.getQuantity());
        dto.setDescription(product.getDescription());
        dto.setDescription(product.getDescription());

        // pick first image if available
        if (!product.getImages().isEmpty()) {
            dto.setImageUrl(product.getImages().get(0));
        }
        return dto;
    }
}
