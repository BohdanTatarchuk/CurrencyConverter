package com.example.converter;

import com.example.converter.service.ConvertsIntoService;

import com.example.converter.service.ExchangeHistoryService;
import com.example.converter.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConverterApplicationTests {

    private final ConvertsIntoService convertsService;
    private final ExchangeHistoryService exchangeHistoryService;
    private final UserService userService;

    @LocalServerPort
    private int port;

    @Autowired
    public ConverterApplicationTests(ConvertsIntoService convertsService, ExchangeHistoryService exchangeHistoryService, UserService userService) {
        this.convertsService = convertsService;
        this.exchangeHistoryService = exchangeHistoryService;
        this.userService = userService;
    }

    @Autowired
    private TestRestTemplate restTemplate;

    //@Test
    //public void test(){
    //    LocalDateTime localDateTime = LocalDateTime.now();
    //    ExchangeHistory exchangeHistory = new ExchangeHistory(
    //            userService.getUserById(1),
    //            convertsService.getConvertsIntoById(1),
    //            localDateTime
    //    );
    //    exchangeHistoryService.saveExchangeHistory(exchangeHistory);
    //}

}
