package com.jp.ebankservice.dto;

import com.jp.ebankservice.entities.*;
import com.jp.ebankservice.model.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor  @AllArgsConstructor @Builder
public class BankAccountRequestDto {
    private double balance;
    private Long customerId;
    private Customer customer;
}
