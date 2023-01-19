package com.freefly.orderservice2.util;

import com.freefly.orderservice2.dto.OrderStatus;
import com.freefly.orderservice2.dto.PurchaseOrderResponseDto;
import com.freefly.orderservice2.dto.RequestContext;
import com.freefly.orderservice2.dto.TransactionRequestDto;
import com.freefly.orderservice2.dto.TransactionStatus;
import com.freefly.orderservice2.entity.PurchaseOrder;

public class EntityDtoUtil {

    public static void setTransactionRequestDto(RequestContext requestContext) {
        TransactionRequestDto dto = new TransactionRequestDto();
        dto.setUserId(requestContext.getPurchaseOrderRequestDto().getUserId());
        dto.setAmount(requestContext.getProductDto().getPrice());
        requestContext.setTransactionRequestDto(dto);
    }

    public static PurchaseOrder getPurchaseOrder(RequestContext requestContext) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setUserId(requestContext.getPurchaseOrderRequestDto().getUserId());
        purchaseOrder.setProductId(requestContext.getPurchaseOrderRequestDto().getProductId());
        purchaseOrder.setAmount(requestContext.getProductDto().getPrice());
        TransactionStatus status = requestContext.getTransactionResponseDto().getStatus();
        OrderStatus orderStatus =
            TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;
        purchaseOrder.setStatus(orderStatus);
        return purchaseOrder;
    }

    public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(
        PurchaseOrder purchaseOrder) {
        PurchaseOrderResponseDto dto = new PurchaseOrderResponseDto();
        dto.setOrderId(purchaseOrder.getId());
        dto.setAmount(purchaseOrder.getAmount());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setUserId(purchaseOrder.getUserId());
        return dto;
    }


}
