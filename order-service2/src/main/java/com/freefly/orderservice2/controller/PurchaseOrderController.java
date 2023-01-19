package com.freefly.orderservice2.controller;

import com.freefly.orderservice2.dto.PurchaseOrderRequestDto;
import com.freefly.orderservice2.dto.PurchaseOrderResponseDto;
import com.freefly.orderservice2.service.OrderFulfillmentService;
import com.freefly.orderservice2.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("order")
@RestController
public class PurchaseOrderController {

    private final OrderFulfillmentService orderFulfillmentService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    public Mono<PurchaseOrderResponseDto> order(
        @RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono) {
        return orderFulfillmentService.processOrder(requestDtoMono);
    }

    @GetMapping("user/{id}")
    public Flux<PurchaseOrderResponseDto> getOrdersByUserId(@PathVariable int userId) {
        return orderQueryService.getProductsByUserId(userId);
    }


}
