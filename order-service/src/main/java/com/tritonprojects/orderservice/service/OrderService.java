package com.tritonprojects.orderservice.service;

import com.tritonprojects.orderservice.dto.InventoryResponseDTO;
import com.tritonprojects.orderservice.dto.OrderLineItemsDTO;
import com.tritonprojects.orderservice.dto.OrderRequestDTO;
import com.tritonprojects.orderservice.model.Order;
import com.tritonprojects.orderservice.model.OrderLineItems;
import com.tritonprojects.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // create constructor for orderRepository
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder; // we will use webClient to make services talk
    public void placeOrder(OrderRequestDTO dto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = dto.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(orderLineItem -> orderLineItem.getSkuCode())
                .toList();

        // Call Inventory Service, and place order if inventory is present
        // webClient helps us make call
        InventoryResponseDTO[] inventoryResponseDTOArray = webClientBuilder.build().get()
                .uri(
                        "http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponseDTO[].class)
                .block();// this block() will ensure that call is synchronous

        boolean allProductsInStock = Arrays.stream(inventoryResponseDTOArray).allMatch(inventoryResponseDTO -> inventoryResponseDTO.getIsInStock());

        if(allProductsInStock) {
            orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is not in stock! See you later...");
        }
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setId(orderLineItemsDTO.getId());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());

        return orderLineItems;
    }

}
