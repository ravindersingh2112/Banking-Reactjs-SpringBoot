package com.example.banking.Controller;

import com.example.banking.Entity.Loan;
import com.example.banking.Service.LoanService;
import com.example.banking.Service.LoanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping("/apply")
    public Loan applyLoan(@RequestBody Loan loan){
    return loanService.applyLoan(loan);
    }

    @GetMapping("/fetch/{loanId}")
    public Loan getLoanById(@PathVariable int loanId){
        return loanService.getLoanById(loanId);
    }

    @GetMapping("/fetchid/{userId}")
    public Optional<List<Loan>> getLoanByUserId(@PathVariable int userId){
        return loanService.getLoanByUser(userId);
    }
}
