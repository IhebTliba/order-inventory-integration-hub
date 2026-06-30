package com.integrationhub.orderservice.service;

import com.integrationhub.orderservice.dto.OrderRequest;
import com.integrationhub.orderservice.dto.OrderResponse;
import com.integrationhub.orderservice.entity.Order;
import com.integrationhub.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .currency(request.getCurrency())
                .build();

        Order saved = orderRepository.save(order);

        // TODO étape Kafka : publier un événement "OrderCreated" ici

        return toResponse(saved);
    }

    private OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .productCode(order.getProductCode())
                .quantity(order.getQuantity())
                .unitPrice(order.getUnitPrice())
                .currency(order.getCurrency())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}