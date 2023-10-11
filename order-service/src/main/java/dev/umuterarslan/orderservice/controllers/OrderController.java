package dev.umuterarslan.orderservice.controllers;

import dev.umuterarslan.orderservice.dto.order.requests.CreateOrderRequest;
import dev.umuterarslan.orderservice.dto.order.responses.CreateOrderResponse;
import dev.umuterarslan.orderservice.dto.order.responses.GetOrdersByUserIdPaginatedResponse;
import dev.umuterarslan.orderservice.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return service.createOrder(request);
    }

    @GetMapping("/user/{id}/{pageNo}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public Page<GetOrdersByUserIdPaginatedResponse> getOrdersByUserId(
            @PathVariable String id,
            @PathVariable int pageNo,
            @PathVariable int pageSize
    ) {
        return service.getOrdersByUserIdPaginated(id, pageNo, pageSize);
    }
}
