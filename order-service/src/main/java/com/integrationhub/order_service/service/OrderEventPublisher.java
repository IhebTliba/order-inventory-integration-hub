package com.integrationhub.order_service.service;

import com.integrationhub.order_service.entity.Order;
import com.integrationhub.order_service.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "order-created-events";

    public void publishOrderCreated(Order order) {
        try {
            log.info("📤 Preparing to publish event for orderId: {}", order.getId());
            
            OrderCreatedEvent event = OrderCreatedEvent.builder()
                    .orderId(order.getId())
                    .productCode(order.getProductCode())
                    .quantity(order.getQuantity())
                    .unitPrice(order.getUnitPrice())
                    .currency(order.getCurrency())
                    .createdAt(order.getCreatedAt())
                    .build();

            kafkaTemplate.send(TOPIC, order.getId().toString(), event);
            log.info("📤 OrderCreatedEvent published for orderId: {}", order.getId());
        } catch (Exception e) {
            log.error("❌ Failed to publish event: {}", e.getMessage(), e);
        }
    }
}