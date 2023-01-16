package com.freeefly.webfluxdemo2.service;

import com.freeefly.webfluxdemo2.SleepUtil;
import com.freeefly.webfluxdemo2.dto.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Response findSquare(int input) {
        return new Response(input * input);
    }

    public List<Response> multiplicationTable(int input) {
        return IntStream.rangeClosed(1, 10)
            .peek(i -> SleepUtil.sleepSeconds(1))
            .peek(i -> System.out.printf("math service processing : %d\n", i))
            .mapToObj(i -> new Response(i * input))
            .collect(Collectors.toList());
    }

}
