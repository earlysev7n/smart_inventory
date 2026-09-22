package com.smartinventory.smartinventory.stockusage;

import com.smartinventory.smartinventory.inventory.InventoryItem;
import com.smartinventory.smartinventory.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "product_stock_usages")
// Connects a product to an inventory item and its required quantity.
public class ProductStockUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    @Column(name = "required_quantity", nullable = false, precision = 19, scale = 3)
    private BigDecimal requiredQuantity;

    protected ProductStockUsage() {
        // Default constructor for JPA
    }

    public ProductStockUsage(
            Product product,
            InventoryItem inventoryItem,
            BigDecimal requiredQuantity) {
        this.product = product;
        this.inventoryItem = inventoryItem;
        this.requiredQuantity = requiredQuantity;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public void setRequiredQuantity(BigDecimal requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }
}