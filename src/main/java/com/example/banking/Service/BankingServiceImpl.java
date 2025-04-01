package com.example.banking.Service;

import com.example.banking.CustomException.UserAlreadyExistsException;
import com.example.banking.CustomException.UserNotExist;
import com.example.banking.CustomException.WithdrawlAmountGreaterThanBalance;
import com.example.banking.Entity.Customer;
import com.example.banking.Repository.BankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankingServiceImpl implements BankingService{

    @Autowired
    BankingRepository bankingRepository;
    //To create account through postman
    public Customer create(Customer customer){
        if(bankingRepository.existsById(customer.getId())){
            throw new UserAlreadyExistsException("User Already Exists");
        }
        return bankingRepository.save(customer);

    }

    //To create multiple accounts through rest template
    public List<Customer> createAll(List<Customer> list){
        for(Customer l:list){
            if(bankingRepository.existsById(l.getId())){
                throw new UserAlreadyExistsException("Cant create The user as User already Exists");
            }
        }

        return bankingRepository.saveAll(list);
    }

    @Override
    public Optional<Customer> displayUser(Integer id) {
        if(!bankingRepository.existsById(id)){
            throw  new UserNotExist("User Does Not Exists");
        }
        return bankingRepository.findById(id);
    }


    //Update Account
    @Override
    public Customer upadateAccount(Integer id, Customer customer) {
    Customer existingCustomer = bankingRepository.findById(id).orElseThrow(()->new UserNotExist("User Does Not Exist"));

    existingCustomer.setBalance(customer.getBalance());
    existingCustomer.setName(customer.getName());
    existingCustomer.setPhoneNumber(customer.getPhoneNumber());
    return bankingRepository.save(existingCustomer);

    }


    @Override
    public Customer depositFunds(Integer id, Integer balance) {
        Customer customer =bankingRepository.findById(id).orElseThrow(()->new UserNotExist("User Does Not Exist"));
        customer.setBalance(balance+ customer.getBalance());
        return bankingRepository.save(customer);
    }



    @Override
    public Customer withdrawlFunds(Integer id, Integer amount) {
        Customer customer =bankingRepository.findById(id).orElseThrow(()->new UserNotExist("User Does Not Exist"));
        if(amount> customer.getBalance()){
            throw new WithdrawlAmountGreaterThanBalance("Cant Withdraw Amount Please Lower The Amount ");
        }
        customer.setBalance(customer.getBalance()-amount);
        return bankingRepository.save(customer);
    }



    @Override
    public Customer transferFunds(Integer senderId, Integer receiverId, Integer amount) {
        Customer sender=bankingRepository.findById(senderId).orElseThrow(()->new UserNotExist("Sender Does Not exist"));
        Customer receiver=bankingRepository.findById(receiverId).orElseThrow(()->new UserNotExist("reciever Does Not exist"));
        if(amount>sender.getBalance()){
            throw new WithdrawlAmountGreaterThanBalance("Insufficient Bank Balance");
        }
        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);
        bankingRepository.save(sender);
        return bankingRepository.save(receiver);

    }

    @Override
    public void deleteAccount(Integer id) {
        bankingRepository.findById(id).orElseThrow(()->new UserNotExist("User Does Not Exists"));
        bankingRepository.deleteById(id);
    }

}
