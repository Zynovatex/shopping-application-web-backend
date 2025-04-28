package com.example.virtual_city.service;


import com.example.virtual_city.dto.ProductDTO;
import com.example.virtual_city.dto.ProductResponseDTO;
import com.example.virtual_city.model.Product;
import com.example.virtual_city.model.Shop;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.ProductRepository;
import com.example.virtual_city.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ShopService shopService;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, ShopService shopService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.shopService = shopService;
    }

    // Updated addProduct to enforce shop ownership
    public Product addProduct(String sellerEmail, ProductDTO dto) {
        User seller = userRepository.findByEmail(sellerEmail)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Seller not found"));

        // Verify seller owns at least one shop
        Shop shop = shopService.verifySellerOwnsShop(seller);

        // Ensure DTO.shopId matches the seller's shop
        if (!shop.getId().equals(dto.getShopId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cannot add product to this shop");
        }

        // Build and persist the Product
        Product product = new Product();
        product.setSeller(seller);
        product.setShop(shop);
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setQuantity(dto.getQuantity());
        product.setBrand(dto.getBrand());
        product.setImages(dto.getImages());
        product.setCategories(dto.getCategories());
        product.setPrice(dto.getPrice());
        product.setDiscountPrice(dto.getDiscountPrice());
        product.setIngredients(dto.getIngredients());
        product.setExpireDate(dto.getExpireDate());
        product.setTaxPrice(dto.getTaxPrice());
        product.setSeoTitle(dto.getSeoTitle());
        product.setSeoDescription(dto.getSeoDescription());
        product.setTags(dto.getTags());
        product.setSizes(dto.getSizes());
        product.setWeight(dto.getWeight());
        product.setHeight(dto.getHeight());
        product.setVolume(dto.getVolume());
        product.setShippingCountries(dto.getShippingCountries());
        product.setEstimatedDeliveryTime(dto.getEstimatedDeliveryTime());
        product.setShippingCost(dto.getShippingCost());

        return productRepository.save(product);
    }

    // NEW: fetch products for a given public shop
    public List<Product> getProductsForShop(Long shopId) {
        Shop shop = shopService.getApprovedShop(shopId);
        return productRepository.findByShop(shop);
    }

    //Get product details from db
    public ProductResponseDTO getProductById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return mapToDto(p);
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ProductResponseDTO mapToDto(Product p) {
        var dto = new ProductResponseDTO();
        dto.setId(p.getId());
        dto.setBrand(p.getBrand());
        dto.setProductName(p.getProductName());
        dto.setDescription(p.getDescription());
        dto.setQuantity(p.getQuantity());
        dto.setPrice(p.getPrice());
        dto.setDiscountPrice(p.getDiscountPrice());
        dto.setIngredients(p.getIngredients());
        dto.setExpireDate(p.getExpireDate());
        dto.setTaxPrice(p.getTaxPrice());
        dto.setSeoTitle(p.getSeoTitle());
        dto.setSeoDescription(p.getSeoDescription());
        dto.setTags(p.getTags());
        dto.setSizes(p.getSizes());
        dto.setWeight(p.getWeight());
        dto.setHeight(p.getHeight());
        dto.setVolume(p.getVolume());
        dto.setShippingCountries(p.getShippingCountries());
        dto.setEstimatedDeliveryTime(p.getEstimatedDeliveryTime());
        dto.setShippingCost(p.getShippingCost());
        dto.setImages(p.getImages());
        dto.setCategories(p.getCategories());
        // …set all other fields…
        return dto;
    }

}
