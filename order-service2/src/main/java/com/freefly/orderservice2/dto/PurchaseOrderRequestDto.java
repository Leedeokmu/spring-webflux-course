package com.freefly.orderservice2.dto;

import lombok.Data;

@Data
public class PurchaseOrderRequestDto {

    private Integer userId;
    private String productId;

}
