package com.smartinventory.smartinventory.stockusage;

import java.util.List;
import java.util.Optional;

import com.smartinventory.smartinventory.inventory.InventoryItem;
import com.smartinventory.smartinventory.inventory.InventoryItemRepository;
import com.smartinventory.smartinventory.product.Product;
import com.smartinventory.smartinventory.product.ProductRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;


@Service
// Contains business logic for managing product stock usage operations.
public class ProductStockUsageService {

    private final ProductStockUsageRepository stockUsageRepository;
    private final ProductRepository productRepository;
    private final InventoryItemRepository inventoryItemRepository;

    public ProductStockUsageService(
            ProductStockUsageRepository stockUsageRepository,
            ProductRepository productRepository,
            InventoryItemRepository inventoryItemRepository) {
        this.stockUsageRepository = stockUsageRepository;
        this.productRepository = productRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    // Creates a new product stock usage record and saves it to the database.
    public ProductStockUsage createStockUsage(
            Long productId,
            Long inventoryItemId,
            BigDecimal requiredQuantity) {

        if (requiredQuantity == null || requiredQuantity.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Required quantity must be greater than zero");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product not found: " + productId));

        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Inventory item not found: " + inventoryItemId));

        ProductStockUsage stockUsage = new ProductStockUsage(
                product,
                inventoryItem,
                requiredQuantity);

        return stockUsageRepository.save(stockUsage);
    }

    // Retrieves all product stock usage records from the database.
    public List<ProductStockUsage> getAllStockUsages() {
        return stockUsageRepository.findAll();
    }

    // Retrieves a product stock usage record by its ID.
    public Optional<ProductStockUsage> getStockUsageById(Long id) {
        return stockUsageRepository.findById(id);
    }

    // Updates an existing product stock usage record with new values.
    public Optional<ProductStockUsage> updateStockUsage(
            Long id,
            Long productId,
            Long inventoryItemId,
            BigDecimal requiredQuantity) {

        if (requiredQuantity == null || requiredQuantity.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Required quantity must be greater than zero");
        }

        return stockUsageRepository.findById(id).map(stockUsage -> {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Product not found: " + productId));

            InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Inventory item not found: " + inventoryItemId));

            stockUsage.setProduct(product);
            stockUsage.setInventoryItem(inventoryItem);
            stockUsage.setRequiredQuantity(requiredQuantity);

            return stockUsageRepository.save(stockUsage);
        });
    }

    // Deletes a product stock usage record by its ID.
    public boolean deleteStockUsage(Long id) {
        return stockUsageRepository.findById(id)
                .map(stockUsage -> {
                    stockUsageRepository.delete(stockUsage);
                    return true;
                })
                .orElse(false);
    }
}