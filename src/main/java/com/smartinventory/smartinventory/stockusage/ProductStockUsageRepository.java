package com.smartinventory.smartinventory.stockusage;

import org.springframework.data.jpa.repository.JpaRepository;


// Repository interface for ProductStockUsage entity, providing CRUD operations.
public interface ProductStockUsageRepository extends JpaRepository<ProductStockUsage, Long> {
    
}
