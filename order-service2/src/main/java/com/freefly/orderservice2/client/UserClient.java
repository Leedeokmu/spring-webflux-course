package com.freefly.orderservice2.client;

import com.freefly.orderservice2.dto.TransactionRequestDto;
import com.freefly.orderservice2.dto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClient {

    private WebClient webClient;

    public UserClient(@Value("${services.user.url}") String url) {
        this.webClient = WebClient.builder()
            .baseUrl(url)
            .build();
    }

    public Mono<TransactionResponseDto> authorizeTransaction(TransactionRequestDto requestDto) {
        return webClient.post()
            .uri("transaction")
            .bodyValue(requestDto)
            .retrieve()
            .bodyToMono(TransactionResponseDto.class);
    }
}
