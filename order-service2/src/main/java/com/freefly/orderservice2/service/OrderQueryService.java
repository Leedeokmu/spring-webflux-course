package com.freefly.orderservice2.service;

import com.freefly.orderservice2.dto.PurchaseOrderResponseDto;
import com.freefly.orderservice2.repository.PurchaseOrderRepository;
import com.freefly.orderservice2.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@Service
public class OrderQueryService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public Flux<PurchaseOrderResponseDto> getProductsByUserId(int userId) {
        return Flux.fromStream(() -> this.purchaseOrderRepository.findByUserId(userId).stream())
            .map(EntityDtoUtil::getPurchaseOrderResponseDto)
            .subscribeOn(Schedulers.boundedElastic())
            ;
    }

}
