package com.freefly.orderservice2.service;

import com.freefly.orderservice2.client.ProductClient;
import com.freefly.orderservice2.client.UserClient;
import com.freefly.orderservice2.dto.PurchaseOrderRequestDto;
import com.freefly.orderservice2.dto.PurchaseOrderResponseDto;
import com.freefly.orderservice2.dto.RequestContext;
import com.freefly.orderservice2.repository.PurchaseOrderRepository;
import com.freefly.orderservice2.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@Service
public class OrderFulfillmentService {

    private final ProductClient productClient;
    private final UserClient userClient;
    private final PurchaseOrderRepository purchaseOrderRepository;


    public Mono<PurchaseOrderResponseDto> processOrder(
        Mono<PurchaseOrderRequestDto> requestDtoMono) {
        /**
         * 구매 주문 요청이 오면
         */
        return requestDtoMono.map(RequestContext::new) // 요청 컨텍스트를 생성하고
            .flatMap(this::productRequestResponse) // 상품 데이터를 조회한 후 컨텍스트에 세팅하고
            .doOnNext(EntityDtoUtil::setTransactionRequestDto) // 결제 요청을 생성하고
            .flatMap(this::userRequestResponse) // 유저 결제 정보 수정을 요청하고 결과를 받아와서 컨텍스트에 세팅하고
            .map(EntityDtoUtil::getPurchaseOrder) // 주문 정보를 만들어서
            .map(purchaseOrderRepository::save) // db 에 저장하고
            .map(EntityDtoUtil::getPurchaseOrderResponseDto) // 응답 정보로 변환하여 리턴
            .subscribeOn(Schedulers.boundedElastic()) // 블로킹 콜이 섞여 있기 때문에 반드시 필요함
            ;
    }

    private Mono<RequestContext> productRequestResponse(RequestContext rc) {
        return productClient.getProductById(rc.getPurchaseOrderRequestDto().getProductId())
            .doOnNext(rc::setProductDto)
            .thenReturn(rc);
    }

    private Mono<RequestContext> userRequestResponse(RequestContext rc) {
        return userClient.authorizeTransaction(rc.getTransactionRequestDto())
            .doOnNext(rc::setTransactionResponseDto)
            .thenReturn(rc);

    }


}
