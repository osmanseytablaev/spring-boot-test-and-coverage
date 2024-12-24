package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    public void testHelloEndpoint() {
        String baseUrl = "http://localhost:" + port;

        // Using a reactive WebClient to test the endpoint
        WebClient webClient = WebClient.create();
        Mono<String> result = webClient
                .get()
                .uri(baseUrl + "/hello")
                .retrieve()
                .bodyToMono(String.class);

        // Wait until we get the response
        String response = result.block();

        // Validate the response
        assertThat(response).isEqualTo("Hello World!");
    }
}

