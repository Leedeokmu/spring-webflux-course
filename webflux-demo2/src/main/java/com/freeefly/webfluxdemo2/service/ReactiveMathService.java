package com.freeefly.webfluxdemo2.service;

import com.freeefly.webfluxdemo2.SleepUtil;
import com.freeefly.webfluxdemo2.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> input * input)
            .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1, 10)
            .doOnNext(i -> SleepUtil.sleepSeconds(1))
            .doOnNext(i -> System.out.printf("reactive math service processing : %d", i))
            .map(i -> new Response(i * input));
    }

}
