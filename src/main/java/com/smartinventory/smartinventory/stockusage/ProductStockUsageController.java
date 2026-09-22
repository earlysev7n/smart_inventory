package com.smartinventory.smartinventory.stockusage;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-stock-usages")
public class ProductStockUsageController {

    private final ProductStockUsageService stockUsageService;

    public ProductStockUsageController(ProductStockUsageService stockUsageService) {
        this.stockUsageService = stockUsageService;
    }

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
}