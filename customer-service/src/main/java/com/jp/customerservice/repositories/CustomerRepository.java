package com.jp.customerservice.repositories;

import com.jp.customerservice.entities.*;
import org.springframework.data.jpa.repository.*;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
