package dev.umuterarslan.orderservice.service;

import dev.umuterarslan.orderservice.dto.order.requests.CreateOrderRequest;
import dev.umuterarslan.orderservice.dto.order.responses.CreateOrderResponse;
import dev.umuterarslan.orderservice.dto.order.responses.GetOrdersByUserIdPaginatedResponse;
import dev.umuterarslan.orderservice.dto.orderitem.requests.CreateOrderItemRequest;
import dev.umuterarslan.orderservice.mapper.OrderServiceMapper;
import dev.umuterarslan.orderservice.model.Order;
import dev.umuterarslan.orderservice.model.OrderItem;
import dev.umuterarslan.orderservice.repository.OrderRepository;
import dev.umuterarslan.orderservice.service.rules.OrderServiceBusinessRules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderServiceMapper orderServiceMapper;
    private final OrderItemService orderItemService;
    private final OrderServiceBusinessRules rules;

    public OrderService(OrderRepository orderRepository, OrderServiceMapper orderServiceMapper, OrderItemService orderItemService, OrderServiceBusinessRules rules) {
        this.orderRepository = orderRepository;
        this.orderServiceMapper = orderServiceMapper;
        this.orderItemService = orderItemService;
        this.rules = rules;
    }

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        request.getItems().forEach(rules::checkIfProductQuantityBiggerThanStock);

        Order saved = orderRepository.save(
                new Order(
                        UUID.randomUUID(),
                        request.getOrderNumber(),
                        request.getUserId(),
                        request.getAddress(),
                        new Date(),
                        List.of()
                )
        );

        List<OrderItem> items = orderItemService.saveAll(request.getItems(), saved);

        saved.getItems().addAll(items);
        orderRepository.save(saved);

        return orderServiceMapper.orderToCreateOrderResponse(orderRepository.findById(saved.getId()).get());
    }

    public Page<GetOrdersByUserIdPaginatedResponse> getOrdersByUserIdPaginated(String userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Order> ordersByUserId = orderRepository.findAllByUserId(userId);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), ordersByUserId.size());

        List<GetOrdersByUserIdPaginatedResponse> responses = orderServiceMapper.ordersToGetOrdersByUserIdPaginatedResponse(ordersByUserId);

        return new PageImpl<>(responses.subList(start, end), pageable, ordersByUserId.size());
    }
}
