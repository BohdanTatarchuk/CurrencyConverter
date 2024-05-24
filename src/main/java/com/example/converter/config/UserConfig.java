package com.example.converter.config;

import com.example.converter.model.User;
import com.example.converter.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner lineRunner (UserRepository repository){
        return args -> {
            User mattew = new User("Mattew",
                    "TGt7xfg7t13gg7");
            User vlad = new User("Vlad",
                    "hih3gz98g4");
            User dima = new User("Dima",
                    "TGt73h9t7g7");
            User nazar = new User("Nazar",
                    "SEDRFGKLJjgh");
            repository.saveAll(List.of(mattew, dima, vlad, nazar));
        };
    }

}
