package com.jp.ebankservice.web;

import com.jp.ebankservice.dto.*;
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
    public List<BankAccountResponseDto> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccountResponseDto getBankAccountById(@PathVariable String id) {
        return bankAccountService.getBankAccountById(id);
    }
    @GetMapping("/accounts/customers/{id}")
    public List<BankAccountResponseDto> getBankAccountByCustomerId(@PathVariable ("id") Long customerId) {
        return bankAccountService.getBankAccountByCustomerId(customerId);
    }
    @Transactional
    @PostMapping("/accounts")
    public BankAccountResponseDto saveBankAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.saveBankAccount(bankAccountRequestDto);
    }

    @Transactional
    @PutMapping("/accounts/{id}")
    public BankAccountResponseDto updateBankAccount(@RequestBody  BankAccountRequestDto bankAccountRequestDto, @PathVariable String id) {
        return bankAccountService.updateBankAccount(bankAccountRequestDto);
    }
    @Transactional
    @DeleteMapping("/accounts/{id}")
    public void deleteBankAccountById(@PathVariable String id) {
        bankAccountService.deleteBankAccountById(id);
    }
}
