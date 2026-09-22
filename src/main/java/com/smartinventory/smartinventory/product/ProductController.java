package com.smartinventory.smartinventory.product;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController 
@RequestMapping ("/api/products")
// Handles HTTP requests for products.
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Creates a new product item and returns the created item.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(
        @RequestBody CreateProductRequest request) {
        return productService.createProduct(request.name(), request.price());
    }

    public record CreateProductRequest(String name, BigDecimal price) {}

    // Retrieves all products from the database.
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Retrieves a product by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Updates an existing product with new values.
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
        @PathVariable Long id,
        @RequestBody UpdateProductRequest request) {
            return productService.updateProduct(
                id,
                request.name(),
                request.price())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        }

    public record UpdateProductRequest(String name,BigDecimal price) {}

}


