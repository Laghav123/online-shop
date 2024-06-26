package com.tritonprojects.orderservice.dto;

import com.tritonprojects.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    List<OrderLineItemsDTO> orderLineItemsDTOList;

}
