package com.smartinventory.smartinventory.stockusage;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-stock-usages")
// Controller for managing product stock usage operations.
public class ProductStockUsageController {

    private final ProductStockUsageService stockUsageService;

    public ProductStockUsageController(ProductStockUsageService stockUsageService) {
        this.stockUsageService = stockUsageService;
    }

    // Handles the creation of a new product stock usage record.
    @PostMapping
    public ResponseEntity<ProductStockUsage> createStockUsage(
            @RequestBody CreateStockUsageRequest request) {

        ProductStockUsage created = stockUsageService.createStockUsage(
                request.productId(),
                request.inventoryItemId(),
                request.requiredQuantity());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    public record CreateStockUsageRequest(
            Long productId,
            Long inventoryItemId,
            BigDecimal requiredQuantity) {
    }

    // Retrieves all product stock usage records.
    @GetMapping
    public List<ProductStockUsage> getAllStockUsages() {
        return stockUsageService.getAllStockUsages();
    }

    // Retrieves a product stock usage record by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<ProductStockUsage> getStockUsageById(@PathVariable Long id) {
        return stockUsageService.getStockUsageById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}