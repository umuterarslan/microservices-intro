package dev.umuterarslan.orderservice.mapper;

import dev.umuterarslan.orderservice.dto.order.responses.CreateOrderResponse;
import dev.umuterarslan.orderservice.dto.order.responses.GetOrdersByUserIdPaginatedResponse;
import dev.umuterarslan.orderservice.dto.orderitem.responses.OrderItemDtoForOrderResponse;
import dev.umuterarslan.orderservice.model.Order;
import dev.umuterarslan.orderservice.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper(componentModel = "spring")
@Repository
public interface OrderServiceMapper {
    @Mapping(target = "items", expression = "java(orderItemsToOrderItemsDtoForOrderResponse(order.getItems()))")
    CreateOrderResponse orderToCreateOrderResponse(Order order);

    @Mapping(target = "items", expression = "java(orderItemsToOrderItemsDtoForOrderResponse(order.getItems()))")
    List<GetOrdersByUserIdPaginatedResponse> ordersToGetOrdersByUserIdPaginatedResponse(List<Order> orders);

    List<OrderItemDtoForOrderResponse> orderItemsToOrderItemsDtoForOrderResponse(List<OrderItem> orderItems);

    OrderItemDtoForOrderResponse toOrderItemDtoForOrderResponse(OrderItem orderItem);
}
