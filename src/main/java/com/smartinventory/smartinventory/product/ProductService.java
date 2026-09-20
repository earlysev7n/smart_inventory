package com.smartinventory.smartinventory.product;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

@Service
// Contains product business operations.
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Creates a new product item and saves it to the database.
    @Transactional 
    public Product createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        return productRepository.save(product);
    }

    // Retrieves all products from the database.
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}