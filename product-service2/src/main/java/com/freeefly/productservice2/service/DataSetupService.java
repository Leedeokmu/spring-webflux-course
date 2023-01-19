package com.freeefly.productservice2.service;

import com.freeefly.productservice2.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {

    private final ProductService service;

    @Override
    public void run(String... args) throws Exception {
        ProductDto productDto1 = new ProductDto("4k-tv", 1000);
        ProductDto productDto2 = new ProductDto("slr-camera", 750);
        ProductDto productDto3 = new ProductDto("iphone", 800);
        ProductDto productDto4 = new ProductDto("headphone", 100);
        Flux.just(productDto1, productDto2, productDto3, productDto4)
            .flatMap(p -> service.insertProduct(Mono.just(p)))
            .subscribe(System.out::println);

    }
}
