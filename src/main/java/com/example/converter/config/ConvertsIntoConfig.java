package com.example.converter.config;

import com.example.converter.service.Implementation.ConvertsIntoServiceImplementation;
import com.example.converter.util.Utils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ConvertsIntoConfig {

    Utils utils = new Utils();

    @Bean
    CommandLineRunner commandLine (ConvertsIntoServiceImplementation service)
    {
        return args -> {
            //service.saveAllConvertsInto(utils.getConvertsIntoList(new File("normal_data.json")));
            service.updateAllConvertsInto(utils.getConvertsIntoList(new File("data.json")));
        };
    }

}
