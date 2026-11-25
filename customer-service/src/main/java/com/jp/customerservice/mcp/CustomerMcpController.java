package com.jp.customerservice.mcp;

import com.jp.customerservice.entities.*;
import com.jp.customerservice.services.*;
import org.springframework.ai.mcp.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Component
public class CustomerMcpController {


    private final CustomerService customerService;

    public CustomerMcpController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @McpTool(name = "getAllCustomers", description = "get all customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @McpTool(name = "getCustomerById", description = "get customer by Id")
    public Customer getCustomerById(
            @McpArg (name = "id", description = "the id of the customer")
            @PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @McpTool(name = "saveCustomer", description = "customer to be saved")
    public Customer saveCustomer(
            @McpArg(name = "customer", description = "the customer to save, information to save are name and email")
            @RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }


   @McpTool(name = "deleteCustomerById", description = "delete customer")
    public void deleteCustomerById(
            @McpArg(name = "id", description = "the id of the customer to delete")
            @PathVariable
            Long id) {
        customerService.deleteCustomerById(id);
    }
}
