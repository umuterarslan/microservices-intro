package dev.umuterarslan.orderservice.service;

import dev.umuterarslan.orderservice.dto.orderitem.requests.CreateOrderItemRequest;
import dev.umuterarslan.orderservice.model.Order;
import dev.umuterarslan.orderservice.model.OrderItem;
import dev.umuterarslan.orderservice.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> saveAll(List<CreateOrderItemRequest> requests, Order order) {
        return orderItemRepository.saveAll(
                requests.stream()
                        .map(request -> new OrderItem(
                                UUID.randomUUID(),
                                request.getProductId(),
                                request.getQuantity(),
                                request.getPrice(),
                                order
                        ))
                        .toList()
        );
    }
}
