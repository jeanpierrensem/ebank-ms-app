package com.jp.customerservice.web;

import com.jp.customerservice.entities.*;
import com.jp.customerservice.services.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class CustomerController {
 private final CustomerService customerService;

 public CustomerController(CustomerService customerService) {
     this.customerService = customerService;
 }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @Transactional
    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    /*@Transactional
    @PutMapping("/customers")
    public Customer updateCustomer(Customer customer) {
        return customerService.updateCustomer(customer);
    }*/

    @Transactional
    @DeleteMapping("/customers/{id}")
    public void deleteCustomerById(Long id) {
        customerService.deleteCustomerById(id);
    }
}

