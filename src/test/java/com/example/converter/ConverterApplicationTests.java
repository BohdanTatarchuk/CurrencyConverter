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
    @DisplayName("USD to USD, amount = 100")
    public void UsdToUsd(){
        Assertions.assertEquals(convertsService.convert("USD", "USD", 100),100);
    }

    @Test
    @DisplayName("USD to PLN, amount = 100")
    public void UsdToPln(){
        Assertions.assertEquals((int)convertsService.convert("USD", "PLN", 100),399);
    }

    @Test
    @DisplayName("PLN to USD, amount = 100")
    public void PlnToUsd(){
        Assertions.assertEquals((int)convertsService.convert("PLN", "USD", 100),25);
    }

    @Test
    @DisplayName("EUR to PLN, amount = 100")
    public void EurToPln(){
        Assertions.assertEquals((int)(convertsService.convert("EUR", "PLN", 100)),432);
    }

    @Test
    @DisplayName("Check Dima")
    void checkUser(){
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/users/1", String.class),
                "{\"id\":1,\"login\":\"Dima\",\"password\":\"281205\"}");
    }

    @Test
    @DisplayName("Delete Dima")
    void deleteUser(){
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/users/", String.class),
                "{\"id\":1,\"login\":\"Dima\",\"password\":\"281205\"}");
    }

}
