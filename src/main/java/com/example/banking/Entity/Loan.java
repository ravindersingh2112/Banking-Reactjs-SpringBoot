package com.example.banking.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="LoanTable")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    private double loanAmount;
    private String loanType;
    private int loanTerm;
    private String loanStatus;
    private double repayAmount;



    @ManyToOne
    @JoinColumn(name="id")
    private Customer customer;
}
