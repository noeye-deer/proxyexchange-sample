package com.example.demo.controller;

import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class RouteController {
    @RequestMapping(value="/**", method={ RequestMethod.GET, RequestMethod.POST })
    public Mono<ResponseEntity<byte[]>> proxy(ServerHttpRequest request, ServerHttpResponse response, ProxyExchange<byte[]> proxy) throws Exception {
        String path = proxy.path("/");
        
        if (request.getMethodValue().startsWith("GET")) {
            return proxy.uri("https://spring.io/" + path).get();
        } else {
            return proxy.uri("https://spring.io/" + path).post();
        }
    }
}