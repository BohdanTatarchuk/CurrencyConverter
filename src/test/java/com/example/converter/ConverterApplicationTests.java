package com.example.converter;

import com.example.converter.service.Implementation.ConvertsIntoServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ConverterApplicationTests {

	@Test
	public void exchangeCheck(ConvertsIntoServiceImplementation service) {
		Assertions.assertEquals(service.convert("USD", "EUR",100), 91.94301793);
	}

}
