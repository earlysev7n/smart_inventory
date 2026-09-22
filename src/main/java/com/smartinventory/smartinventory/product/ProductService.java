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

    // Retrieves a product by its ID.
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Updates an existing product with new values.
    @Transactional 
    public Optional<Product> updateProduct(
        Long id,
        String name,
        BigDecimal price) {
            return productRepository.findById(id).map(product -> {
                product.setName(name);
                product.setPrice(price);
                return productRepository.save(product);
            });
        }

    // Deletes a product by its ID.
    @Transactional 
    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
        .map(product -> {
            productRepository.delete(product);
            return true;
        })
        .orElse(false);
    }

}