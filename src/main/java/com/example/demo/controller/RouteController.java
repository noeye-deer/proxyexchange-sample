package com.example.demo.controller;

import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class RouteController {
    
    @RequestMapping(value="/**", method={ RequestMethod.GET, RequestMethod.POST })
    public Mono<String> proxy(ServerHttpRequest request, ServerHttpResponse response, ProxyExchange<byte[]> proxy) throws Exception {
        String path = proxy.path("/");
        
        if (request.getMethodValue().startsWith("GET")) {
            return WebClient
                    .builder()
                    .baseUrl("https://spring.io/")
                    .build()
                    .get()
                    .uri(path)
                    .retrieve().bodyToMono(String.class);
        } else {
            return WebClient
                    .builder()
                    .baseUrl("https://spring.io/)
                    .build()
                    .post()
                    .uri(path)
                    .retrieve().bodyToMono(String.class);
        }
    }
}
