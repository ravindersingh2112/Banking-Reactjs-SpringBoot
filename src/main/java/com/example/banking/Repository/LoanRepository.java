package com.example.banking.Repository;

import com.example.banking.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
    Optional<List<Loan>> findByCustomer_Id(int customerId);
}
