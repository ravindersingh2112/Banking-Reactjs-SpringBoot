package com.example.banking.Service;

import com.example.banking.Entity.Customer;

import java.util.List;
import java.util.Optional;


public interface BankingService {

    public Customer create(Customer customer);
    public List<Customer> createAll(List<Customer> list);
    public Optional<Customer> displayUser(Integer id);
    public Customer upadateAccount(Integer id, Customer customer);
    public Customer depositFunds(Integer id, Integer balance);
    public Customer withdrawlFunds(Integer id, Integer amount);
    public Customer transferFunds(Integer senderId, Integer receiverId, Integer amount);
    public void deleteAccount(Integer Id);
}
