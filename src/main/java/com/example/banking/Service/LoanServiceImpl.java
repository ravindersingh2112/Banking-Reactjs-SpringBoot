package com.example.banking.Service;

import com.example.banking.CustomException.UserNotExist;
import com.example.banking.Entity.Loan;
import com.example.banking.Repository.BankingRepository;
import com.example.banking.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    BankingRepository bankingRepository;

    @Autowired
    LoanRepository loanRepository;

    @Override
    public Loan applyLoan(Loan loan) {
        bankingRepository.findById(loan.getCustomer().getId()).orElseThrow(()->new UserNotExist("User Does not Exists"));
        loan.setRepayAmount(loan.getLoanAmount()+(loan.getLoanAmount()*loan.getLoanTerm()*0.10));
        loan.setLoanStatus("Approved");
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(int id) {
        return loanRepository.findById(id).orElseThrow(()->new UserNotExist("Loan id does not Exist"));
    }

    @Override
    public Optional<List<Loan>> getLoanByUser(int id) {
        return loanRepository.findByCustomer_Id(id);
    }
}
