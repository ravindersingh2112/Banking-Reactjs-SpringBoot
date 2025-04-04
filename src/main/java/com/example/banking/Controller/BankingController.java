package com.example.banking.Controller;


import com.example.banking.Entity.Customer;
import com.example.banking.Service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class BankingController {
        
    @Autowired
    BankingService bankingService;


    RestTemplate restTemplate=new RestTemplate();
    String url="https://67ca78b9102d684575c5703b.mockapi.io/banking/v1/users";


    //Create user through the postman
    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer){
        return bankingService.create(customer);
    }

    //Create multiple user through the RestTemplate 
    @GetMapping("/saveAll")
    public List<Customer> saveAll(){
       Customer[] customers = restTemplate.getForObject(url, Customer[].class);
       List<Customer> customer = Arrays.asList(customers);
        return bankingService.createAll(customer);
    }

    //fetch details using pathvariable
    @GetMapping("/details/{id}")
    public Optional<Customer> displayUser(@PathVariable Integer id){
        return bankingService.displayUser(id);
    }

    //Update user details
    @PostMapping("/update/{id}")
    public Customer updateUser(@PathVariable Integer id, @RequestBody Customer customer){
        return bankingService.upadateAccount(id, customer);
    }

    //Using Get mapping because not using the json body for the data
    @GetMapping("/deposit")
    public Customer depositFunds(@RequestParam Integer id, @RequestParam Integer balance){
        return bankingService.depositFunds(id,balance);
    }

    //WithDraw amount from the account
    @GetMapping("/withdrawl")
    public Customer withdrawlFund(@RequestParam Integer id, @RequestParam Integer amount){
        return bankingService.withdrawlFunds(id,amount);
    }

    //Transfer funds to one user to another
    @GetMapping("/transfer")
    public Customer transferFund(@RequestParam Integer senderId, @RequestParam Integer recieverId, @RequestParam Integer amount){
        return bankingService.transferFunds(senderId,recieverId,amount);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        bankingService.deleteAccount(id);
        return "Account has been deleted";
    }

}
