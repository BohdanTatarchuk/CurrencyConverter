package com.example.converter;

import com.example.converter.service.ConvertsIntoService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConverterApplicationTests {

    private final ConvertsIntoService convertsService;

    @LocalServerPort
    private int port;

    @Autowired
    public ConverterApplicationTests(ConvertsIntoService convertsService) {
        this.convertsService = convertsService;
    }

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @DisplayName("Check Dima")
    void checkUser(){
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/users/2", String.class),
                "{\"id\":2,\"login\":\"Dima\",\"password\":\"281205\"}");
    }
}
