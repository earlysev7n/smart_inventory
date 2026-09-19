package com.smartinventory.smartinventory.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

// Provides database operations for InventoryItem entities.
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
