package com.jp.ebankservice.repositories;

import com.jp.ebankservice.entities.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCustomerId(Long customerId);
}
