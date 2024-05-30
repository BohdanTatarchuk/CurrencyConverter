package com.example.converter;

import com.example.converter.service.RestService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;


@SpringBootTest
class ConverterApplicationTests {

	@Value("${api.base.url}")
	private String apiUrl;

	@Value("${api.key}")
	private String apiKey;

	@Test
	void RestConnection() throws IOException, URISyntaxException, InterruptedException {
		RestService restService = new RestService();
		restService.getResult(restService.connect(apiKey, apiUrl));
	}

}
