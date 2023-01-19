package com.freefly.orderservice2.dto;

import lombok.Data;

@Data
public class TransactionRequestDto {

    private Integer userId;
    private Integer amount;

}
