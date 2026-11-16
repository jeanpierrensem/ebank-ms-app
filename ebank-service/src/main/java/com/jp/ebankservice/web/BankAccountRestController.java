package com.jp.ebankservice.web;

import com.jp.ebankservice.entities.*;
import com.jp.ebankservice.services.*;
import jakarta.transaction.*;
import jakarta.ws.rs.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BankAccountRestController {
    private final BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }



    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return bankAccountService.getBankAccountById(id);
    }
@GetMapping("/accounts/customers/{id}")
    public List<BankAccount> getBankAccountByCustomerId(@PathVariable ("id") Long customerId) {
        return bankAccountService.getBankAccountByCustomerId(customerId);
    }

    @Transactional
    @PostMapping("/accounts")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.saveBankAccount(bankAccount);
    }

    @Transactional
    @PutMapping("/accounts/{id}")
    public BankAccount updateBankAccount(@RequestBody  BankAccount bankAccount, @PathVariable String id) {
        BankAccount bankAccountToUpdate = bankAccountService.getBankAccountById(id);
        bankAccountToUpdate.setBalance(bankAccount.getBalance());
        bankAccountToUpdate.setType(bankAccount.getType());
        bankAccountToUpdate.setCustomer(bankAccount.getCustomer());
        bankAccountToUpdate.setCustomerId(bankAccount.getCustomerId());
        return bankAccountService.updateBankAccount(bankAccountToUpdate);
    }

    @Transactional
    @DeleteMapping("/accounts/{id}")
    public void deleteBankAccountById(@PathVariable String id) {
        bankAccountService.deleteBankAccountById(id);
    }



}
