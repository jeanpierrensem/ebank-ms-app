package com.jp.ebankservice.feign;



import com.jp.ebankservice.model.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient("customer-service")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable long id);
}
