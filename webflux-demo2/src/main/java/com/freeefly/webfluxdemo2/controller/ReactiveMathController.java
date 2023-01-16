package com.freeefly.webfluxdemo2.controller;

import com.freeefly.webfluxdemo2.dto.Response;
import com.freeefly.webfluxdemo2.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
@RequiredArgsConstructor
public class ReactiveMathController {

    private final ReactiveMathService mathService;

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable("input") int input) {
        return this.mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable("input") int input) {
        return this.mathService.multiplicationTable(input);
    }


}
