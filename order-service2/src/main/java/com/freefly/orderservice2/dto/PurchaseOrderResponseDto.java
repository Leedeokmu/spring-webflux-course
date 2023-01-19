package com.freefly.orderservice2.dto;


import lombok.Data;

@Data
public class PurchaseOrderResponseDto {

    private Integer orderId;
    private Integer userId;
    private String productId;
    private Integer amount;
    private OrderStatus status;

}
