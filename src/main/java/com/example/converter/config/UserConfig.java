package com.example.converter.config;

import com.example.converter.model.ConvertsInto;
import com.example.converter.model.Currency;
import com.example.converter.model.User;
import com.example.converter.repository.UserRepository;
import com.example.converter.service.Implementation.ConvertsIntoServiceImplementation;
import com.example.converter.service.Implementation.CurrencyServiceImplementation;
import com.example.converter.service.Implementation.UserServiceImplementation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner lineRunner (UserServiceImplementation repository,
                                  CurrencyServiceImplementation currencyServiceImplementation,
                                  ConvertsIntoServiceImplementation convertsIntoServiceImplementation){
        return args -> {
            User mattew = new User("Mattew", "db3iz1b3");
            User vlad = new User("Vlad", "hih3gz98g4");
            User dima = new User("Dima", "TGt73h9t7g7");
            User nazar = new User("Nazar","SEDRFGKLJjgh");

            Currency uah = new Currency("UAH", "Hryvnia");
            Currency usd = new Currency("USD", "Dollar");

            ConvertsInto uah_usd = new ConvertsInto("UAH", "USD", 0.4);

            convertsIntoServiceImplementation.saveAllConvertsInto(List.of(uah_usd));
            convertsIntoServiceImplementation.updateConvertsInto(1, new ConvertsInto("USD", "UAH", 40));

            //currencyServiceImplementation.saveAllCurrencies(List.of(uah,usd)); //TODO: data is not updating
            //currencyServiceImplementation.updateCurrencyById("UAH",new Currency("UAH", "dfpolgj"));

            repository.saveAllUsers(List.of(mattew, dima, vlad, nazar));
            repository.updateUserById(1, new User("Sonia", "e4"));
        };

    }
}
