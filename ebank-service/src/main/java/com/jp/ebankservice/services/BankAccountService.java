package com.jp.ebankservice.services;

import com.jp.ebankservice.dto.*;
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

    public List<BankAccountResponseDto> getAllBankAccounts() {
          return mappers.fromBankAccounts(bankAccountRepository.findAll());
    }

    public BankAccountResponseDto getBankAccountById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Bank account not found"));
        return mappers.fromBankAccount(bankAccount);
    }

    public List<BankAccountResponseDto> getBankAccountByCustomerId(Long customerId) {
        return mappers.fromBankAccounts(bankAccountRepository.findByCustomerId(customerId));
    }

    @Transactional
    public BankAccountResponseDto saveBankAccount(BankAccountRequestDto bankAccountRequestDto) {

        BankAccount bankAccount =  bankAccountRepository.save(BankAccount.builder()
                        .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .balance(bankAccountRequestDto.getBalance())
                        .createdAt(LocalDateTime.now())
                        .customerId(bankAccountRequestDto.getCustomerId())
                .build());
        return mappers.fromBankAccount(bankAccount);

    }
    @Transactional
    public BankAccountResponseDto updateBankAccount(BankAccountRequestDto bankAccountRequestDto) {
        BankAccount bankAccount =  bankAccountRepository.save(BankAccount.builder()
                .balance(bankAccountRequestDto.getBalance())
                .build());
        return mappers.fromBankAccount(bankAccount);
    }
    @Transactional
    public void deleteBankAccountById(String id) {
        bankAccountRepository.deleteById(id);
    }



}
