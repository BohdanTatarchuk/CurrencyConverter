package com.example.converter.config;

import com.example.converter.service.Implementation.ConvertsIntoServiceImplementation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLine (ConvertsIntoServiceImplementation service)
    {
        return args -> {
            //service.updateAllConvertsInto(utils.getConvertsIntoList(new File("normal_data.json")));
            //System.out.println(service.convert("PLN","USD",100));
            System.out.println(service.getAllConvertsInto());
        };
    }

}
