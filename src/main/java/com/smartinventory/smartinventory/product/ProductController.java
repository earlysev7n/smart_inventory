package com.smartinventory.smartinventory.product;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController 
@RequestMapping ("/api/products")
// Handles HTTP requests for products.
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Retrieves all products from the database.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(
        @RequestBody CreateProductRequest request) {
        return productService.createProduct(request.name(), request.price());
    }

    public record CreateProductRequest(String name, BigDecimal price) {}
}
