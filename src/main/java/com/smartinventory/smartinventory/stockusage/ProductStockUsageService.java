package com.smartinventory.smartinventory.stockusage;

import com.smartinventory.smartinventory.inventory.InventoryItem;
import com.smartinventory.smartinventory.inventory.InventoryItemRepository;
import com.smartinventory.smartinventory.product.Product;
import com.smartinventory.smartinventory.product.ProductRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
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
}