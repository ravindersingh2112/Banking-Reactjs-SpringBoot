package com.example.banking.Service;

import com.example.banking.Entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Loan applyLoan(Loan loan);
    Loan getLoanById(int id);
    Optional<List<Loan>> getLoanByUser(int id);
}
