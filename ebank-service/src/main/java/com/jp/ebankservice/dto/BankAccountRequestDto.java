package com.jp.ebankservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor  @AllArgsConstructor @Builder
public class BankAccountRequestDto {
    private double balance;
    private Long customerId;
}
