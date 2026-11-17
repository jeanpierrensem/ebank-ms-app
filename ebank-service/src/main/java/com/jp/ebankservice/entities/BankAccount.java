package com.jp.ebankservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountType type ;
    private Long customerId;

    @Transient
    private Customer customer;
}
record Customer (Long id, String firstName, String lastName) { }
