package com.example.banking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class Customer {
    @Id
    Integer id;
    String name;
    Integer balance;
    String phoneNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer(){
        
    }
    public Customer(Integer id, String name, Integer balance, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
    }
}
