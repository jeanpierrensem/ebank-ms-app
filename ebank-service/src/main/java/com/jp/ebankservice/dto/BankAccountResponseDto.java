package com.jp.ebankservice.dto;
import com.jp.ebankservice.entities.*;
import lombok.*;

import java.time.*;

@Getter @Setter @NoArgsConstructor  @AllArgsConstructor @Builder
public class BankAccountResponseDto {
    private String id ;
    private LocalDateTime createdAt;
    private  double balance;
    private   AccountType type;
    private   Long customerId;
}