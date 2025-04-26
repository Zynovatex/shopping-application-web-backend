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

    public Product addProduct(String sellerEmail, ProductDTO productDTO) {
        User seller = userRepository.findByEmail(sellerEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seller not found"));

        // ✅ Ensure the seller owns a shop
        Shop shop = shopService.verifySellerOwnsShop(seller);

        Product product = new Product();
        product.setSeller(seller);
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setBrand(productDTO.getBrand());
        product.setImages(productDTO.getImages());  // ✅ Store Firebase URLs as strings
        product.setCategories(productDTO.getCategories());
        product.setPrice(productDTO.getPrice());
        product.setDiscountPrice(productDTO.getDiscountPrice());
        product.setIngredients(productDTO.getIngredients());
        product.setExpireDate(productDTO.getExpireDate());
        product.setTaxPrice(productDTO.getTaxPrice());
        product.setSeoTitle(productDTO.getSeoTitle());
        product.setSeoDescription(productDTO.getSeoDescription());
        product.setTags(productDTO.getTags());
        product.setSizes(productDTO.getSizes());
        product.setWeight(productDTO.getWeight());
        product.setHeight(productDTO.getHeight());
        product.setVolume(productDTO.getVolume());
        product.setShippingCountries(productDTO.getShippingCountries());
        product.setEstimatedDeliveryTime(productDTO.getEstimatedDeliveryTime());
        product.setShippingCost(productDTO.getShippingCost());

        return productRepository.save(product);
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
