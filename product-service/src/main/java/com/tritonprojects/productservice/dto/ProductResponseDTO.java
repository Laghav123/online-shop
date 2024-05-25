package com.tritonprojects.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data //used to create getters and setters
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponseDTO {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
