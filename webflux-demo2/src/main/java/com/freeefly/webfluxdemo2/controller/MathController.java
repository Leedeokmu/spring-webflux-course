package com.freeefly.webfluxdemo2.controller;

import com.freeefly.webfluxdemo2.dto.Response;
import com.freeefly.webfluxdemo2.service.MathService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("math")
@RequiredArgsConstructor
public class MathController {

    private final MathService mathService;

    @GetMapping("square/{input}")
    public Response findSquare(@PathVariable("input") int input) {
        return this.mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public List<Response> multiplicationTable(@PathVariable("input") int input) {
        return this.mathService.multiplicationTable(input);
    }
}
