package dev.umuterarslan.orderservice.repository;

import dev.umuterarslan.orderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
