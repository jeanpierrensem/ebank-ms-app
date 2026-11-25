package com.jp.customerservice;

import com.jp.customerservice.entities.*;
import com.jp.customerservice.repositories.*;
import com.jp.customerservice.services.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerService customerService) {
        return args -> {
            List.of("Yassine", "Guy", "paulin", "thiery",   "Atma", "Nsem", "Assen", "Belavi", "pidinsem", "Guantanomo", "stephane", "fabien", "Ernest", "Paul", "Alain ").forEach(name -> {

                customerService.saveCustomer(Customer.builder()
                                .name(name)
                                .email(name.concat("@gmail.com"))
                        .build());


            });

        };

    }

}
