package com.jp.ebankservice.dto;

import com.jp.ebankservice.entities.*;
import com.jp.ebankservice.services.*;
import jakarta.transaction.*;

import java.util.*;

public class mappers {

    public static final List<BankAccountResponseDto> fromBankAccounts( List<BankAccount> bankAccounts) {
       List<BankAccountResponseDto> dtos = new ArrayList<>();
       BankAccountResponseDto dto ;
       bankAccounts.forEach( bankAccount -> {
           dtos.add(BankAccountResponseDto.builder()
                   .type(bankAccount.getType())
                           .createdAt(bankAccount.getCreatedAt())
                           .balance(bankAccount.getBalance())
                           .customerId(bankAccount.getCustomerId())
                           .customer(bankAccount.getCustomer())
                           .id(bankAccount.getId())
                   .build());
       });
     return dtos;
    }

    public static final BankAccountResponseDto fromBankAccount(BankAccount bankAccount) {
       return BankAccountResponseDto.builder()
               .createdAt(bankAccount.getCreatedAt())
               .balance(bankAccount.getBalance())
               .customerId(bankAccount.getCustomerId())
               .id(bankAccount.getId())
               .customer(bankAccount.getCustomer())
               .build();
    }

  public static final  BankAccount toBankAccount(BankAccountRequestDto dto) {
        return BankAccount.builder()
                .balance(dto.getBalance())
                .customerId(dto.getCustomerId())
                .build();
  }


}
