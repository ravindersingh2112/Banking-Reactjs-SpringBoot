package com.example.banking.Repository;

import com.example.banking.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankingRepository extends JpaRepository<Customer, Integer> {



}
