package com.freeefly.productservice2.service;

import com.freeefly.productservice2.dto.ProductDto;
import com.freeefly.productservice2.repository.ProductRepository;
import com.freeefly.productservice2.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Sinks.Many<ProductDto> sink;

    public Flux<ProductDto> getAll() {
        return this.productRepository.findAll()
            .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return this.productRepository.findById(id)
            .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(EntityDtoUtil::toEntity)
            .flatMap(this.productRepository::insert)
            .map(EntityDtoUtil::toDto)
            .doOnNext(sink::tryEmitNext);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono) {
        return productRepository.findById(id)
            .flatMap(p -> productDtoMono.map(EntityDtoUtil::toEntity)
                .doOnNext(e -> e.setId(id))
            ).flatMap(productRepository::save)
            .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteProductByd(String id) {
        return productRepository.deleteById(id);
    }

    public Flux<ProductDto> getProductByPriceRange(int min, int max) {
        return productRepository.findByPriceBetween(Range.closed(min, max))
            .map(EntityDtoUtil::toDto);
    }


}
