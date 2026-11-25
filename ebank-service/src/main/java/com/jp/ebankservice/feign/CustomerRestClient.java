package com.jp.ebankservice.feign;



import com.jp.ebankservice.model.*;
import io.github.resilience4j.circuitbreaker.annotation.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "customer-service")
@CircuitBreaker( name = "customer-service", fallbackMethod = "getDefaultCustomer")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable long id);

    default Customer getDefaultCustomer(long id, Exception e) {
       return  Customer.builder()
               .id(id)
               .name("default-customer")
               .email("default-customer@gmail.com")
               .build();
    }
}