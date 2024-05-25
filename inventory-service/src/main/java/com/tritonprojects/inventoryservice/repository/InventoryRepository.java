package com.tritonprojects.inventoryservice.repository;

import com.tritonprojects.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // JpaRepository takes two generic arguments
    // first one is type of object you want to store
    // second one is the data type of primary key
    Optional<Inventory> findBySkuCode(String skuCode);
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
