package com.tritonprojects.inventoryservice.controller;

import com.tritonprojects.inventoryservice.dto.InventoryResponseDTO;
import com.tritonprojects.inventoryservice.repository.InventoryRepository;
import com.tritonprojects.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // http://localhost:8082/api/inventory/iphone-13,iphone-13-red  -> if we concanate in string and send
    // http://localhost:8082/api/inventory?sku-code=iphone-13&sku-code=iphone-13-red -> if as req param
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDTO> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
