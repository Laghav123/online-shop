package com.tritonprojects.productservice.service;

import com.tritonprojects.productservice.dto.ProductRequestDTO;
import com.tritonprojects.productservice.dto.ProductResponseDTO;
import com.tritonprojects.productservice.model.Product;
import com.tritonprojects.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


// This file is just like "Impl" files in ZL Rest APIs
@Service
@RequiredArgsConstructor // will create constructor for required parameters like productRepository
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequestDTO dto){
        Product product = Product.builder().
                name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponseDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponseDTO mapToProductResponse(Product product){
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }


}
