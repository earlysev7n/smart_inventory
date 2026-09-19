package com.smartinventory.smartinventory.inventory;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/inventory-items")
// Handles HTTP requests for inventory items.
public class InventoryItemController {
    
    private final InventoryItemService inventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    // Creates a new inventory item and returns the created item.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryItem createInventoryItem(
            @RequestBody CreateInventoryItemRequest request) {
        return inventoryItemService.createInventoryItem(request.name(), request.quantity(), request.unit());
    }

    public record CreateInventoryItemRequest(String name, BigDecimal quantity, String unit) {}

    // Retrieves all inventory items from the database.
    @GetMapping
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemService.getAllInventoryItems();
    }

    // Retrieves an inventory item by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long id) {
        return inventoryItemService.getInventoryItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Updates an existing inventory item with new values.
    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateInventoryItem(
        @PathVariable Long id,
        @RequestBody UpdateInventoryItemRequest request) {
            return inventoryItemService.updateInventoryItem(
                id,
                request.name(),
                request.quantity(),
                request.unit())
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Represents the request body for updating an inventory item.
    public record UpdateInventoryItemRequest(String name, BigDecimal quantity, String unit) {}

    // Deletes an inventory item by its ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id){
        if (inventoryItemService.deleteInventoryItem(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
