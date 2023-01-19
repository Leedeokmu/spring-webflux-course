package com.freefly.orderservice2.client;

import com.freefly.orderservice2.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {

    private WebClient webClient;

    public ProductClient(@Value("${services.product.url}") String url) {
        this.webClient = WebClient.builder()
            .baseUrl(url)
            .build();
    }

    public Mono<ProductDto> getProductById(final String productId) {
        return webClient.get()
            .uri("{id}", productId)
            .retrieve()
            .bodyToMono(ProductDto.class);
    }

}
