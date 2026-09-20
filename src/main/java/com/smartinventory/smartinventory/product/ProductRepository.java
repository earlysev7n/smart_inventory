package com.smartinventory.smartinventory.product;

import org.springframework.data.jpa.repository.JpaRepository;

// Provides database operations for Product entities.
public interface ProductRepository extends JpaRepository<Product, Long> { 
}
