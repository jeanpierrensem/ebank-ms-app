package com.jp.ebankservice;

import com.jp.ebankservice.dto.*;
import com.jp.ebankservice.entities.*;
import com.jp.ebankservice.repositories.*;
import com.jp.ebankservice.services.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;

import java.time.*;
import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BankAccountService bankAccountService) {
        return args -> {

            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 5; j++) {
                    bankAccountService.saveBankAccount(BankAccountRequestDto.builder()
                            .balance(500+Math.random()*50000)
                            .customerId( Long.parseLong(i+""))
                            .build());
                }
            }
        };
    }

}
