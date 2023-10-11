package dev.umuterarslan.orderservice.service.rules;

import dev.umuterarslan.orderservice.dto.orderitem.requests.CreateOrderItemRequest;
import dev.umuterarslan.orderservice.exceptions.ProductQuantityBiggerThanStockException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderServiceBusinessRules {
    private final WebClient webClient;

    public OrderServiceBusinessRules(WebClient webClient) {
        this.webClient = webClient;
    }

    public void checkIfProductQuantityBiggerThanStock(CreateOrderItemRequest request){
        String url = "http://localhost:8080/api/v1/products/stock/{productId}/{quantity}";
        String exceptionMessage = String.format(
                "Requested quantity (%d) bigger than stock for product (%s)",
                request.getQuantity(),
                request.getProductId()
        );

        //! If the product quantity is bigger than the stock returns TRUE, otherwise returns FALSE
        Boolean isBigger = webClient.get()
                .uri(url, request.getProductId(), request.getQuantity())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(isBigger)) throw new ProductQuantityBiggerThanStockException(exceptionMessage);
    }
}
