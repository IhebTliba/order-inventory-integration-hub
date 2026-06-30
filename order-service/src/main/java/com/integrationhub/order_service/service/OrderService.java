package com.integrationhub.order_service.service;

import com.integrationhub.order_service.dto.OrderRequest;
import com.integrationhub.order_service.dto.OrderResponse;
import com.integrationhub.order_service.entity.Order;
import com.integrationhub.order_service.repository.OrderRepository;
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
