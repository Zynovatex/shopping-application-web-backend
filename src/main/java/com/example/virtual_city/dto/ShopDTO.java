package com.example.virtual_city.dto;

import lombok.Data;
import java.util.List;

@Data
public class ShopDTO {

    private String shopName;
    private String address;
    private String category;
    private String district;
    private String area;
    private String shopType;
    private boolean deliveryAvailable;
    private String description;
    private List<String> shopImages; // ✅ Receive Image URLs from Frontend
    private String registrationNumber;
    private String taxNumber;
    private boolean vatRegistered;
    private String registrationType;
    private List<String> registrationDocuments; // ✅ Receive Document URLs from Frontend
    private boolean cashOnDelivery;
    private boolean onlinePayment;
    private boolean mobilePayment;

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isDeliveryAvailable() {
        return deliveryAvailable;
    }

    public void setDeliveryAvailable(boolean deliveryAvailable) {
        this.deliveryAvailable = deliveryAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getShopImages() {
        return shopImages;
    }

    public void setShopImages(List<String> shopImages) {
        this.shopImages = shopImages;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public boolean isVatRegistered() {
        return vatRegistered;
    }

    public void setVatRegistered(boolean vatRegistered) {
        this.vatRegistered = vatRegistered;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public List<String> getRegistrationDocuments() {
        return registrationDocuments;
    }

    public void setRegistrationDocuments(List<String> registrationDocuments) {
        this.registrationDocuments = registrationDocuments;
    }

    public boolean isCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(boolean cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public boolean isOnlinePayment() {
        return onlinePayment;
    }

    public void setOnlinePayment(boolean onlinePayment) {
        this.onlinePayment = onlinePayment;
    }

    public boolean isMobilePayment() {
        return mobilePayment;
    }

    public void setMobilePayment(boolean mobilePayment) {
        this.mobilePayment = mobilePayment;
    }
}
