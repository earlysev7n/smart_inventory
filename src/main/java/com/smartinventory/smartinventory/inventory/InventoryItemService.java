package com.smartinventory.smartinventory.inventory;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

@Service
// Contains inventory business operations.
public class InventoryItemService {
    
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    // Creates a new inventory item and saves it to the database.
    @Transactional 
    public InventoryItem createInventoryItem(String name, BigDecimal quantity, String unit) {
        InventoryItem inventoryItem = new InventoryItem(name, quantity, unit);
        return inventoryItemRepository.save(inventoryItem);
    }

    // Retrieves all inventory items from the database.
    @Transactional(readOnly = true)
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    // Retrieves an inventory item by its ID.
    @Transactional(readOnly = true)
    public Optional<InventoryItem> getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id);
    }

    // Updates an existing inventory item with new values.
    @Transactional
    public Optional<InventoryItem> updateInventoryItem(
        Long id,
        String name,
        BigDecimal quantity,
        String unit) {
            return inventoryItemRepository.findById(id).map(inventoryItem -> {
                inventoryItem.setName(name);
                inventoryItem.setQuantity(quantity);
                inventoryItem.setUnit(unit);
                return inventoryItemRepository.save(inventoryItem);
            });
        }

    // Deletes an inventory item by its ID.
    @Transactional 
    public boolean deleteInventoryItem(Long id) {
        return inventoryItemRepository.findById(id)
        .map(inventoryItem -> {
            inventoryItemRepository.delete(inventoryItem);
        return true;
        })
        .orElse(false);
    }
}
