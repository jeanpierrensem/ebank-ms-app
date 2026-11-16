package com.jp.ebankservice.services;

import com.jp.ebankservice.entities.*;
import com.jp.ebankservice.repositories.*;
import jakarta.transaction.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id) {
        return bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Bank account not found"));
    }

    public List<BankAccount> getBankAccountByCustomerId(Long customerId) {
        return bankAccountRepository.findByCustomerId(customerId);
    }

    @Transactional
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        bankAccount.setType( Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT);
        bankAccount.setCreatedAt(LocalDateTime.now());
        return bankAccountRepository.save(bankAccount);
    }
    @Transactional
    public BankAccount updateBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
    @Transactional
    public void deleteBankAccountById(String id) {
        bankAccountRepository.deleteById(id);
    }



}
