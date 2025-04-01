package com.example.banking;

import com.example.banking.CustomException.UserNotExist;
import com.example.banking.CustomException.WithdrawlAmountGreaterThanBalance;
import com.example.banking.Entity.Customer;
import com.example.banking.Repository.BankingRepository;
import com.example.banking.Service.BankingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankingApplicationTests {

	@Mock
	private BankingRepository bankingRepository;

	@InjectMocks
	private BankingServiceImpl bankingService;


	private Customer customer1;
	private Customer customer2;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customer1 =new Customer(1,"Ravinder",1000,"7023368369");
		customer2 =new Customer(2,"Sucharitha",1000,"7023368370");
	}


	@Test
	void testCreateAccount(){
		when(bankingRepository.save(any(Customer.class))).thenReturn(customer1);
		Customer createdCustomer = bankingService.create(customer1);
		assertNotNull(createdCustomer);
		assertEquals("Ravinder", createdCustomer.getName());
		assertEquals(1000, createdCustomer.getBalance());
	}

	@Test
	void testUserNotFound(){

		assertThrows(UserNotExist.class,()->{
			bankingService.displayUser(1);
		});

	}


	@Test
	void testUpdateAccount(){
		Customer updatedCustomer =new Customer(1,"Ravi",1000,"7017062134");
		when(bankingRepository.findById(1)).thenReturn(Optional.of(customer1));
		when(bankingRepository.save(any(Customer.class))).thenReturn(updatedCustomer);
		Customer result= bankingService.upadateAccount(1, updatedCustomer);
		assertNotNull(updatedCustomer);
		assertEquals("Ravi", updatedCustomer.getName());
		assertEquals(1000, updatedCustomer.getBalance());
	}

	@Test
	void testDeposit(){
		when(bankingRepository.findById(1)).thenReturn(Optional.of(customer1));
		when(bankingRepository.save(any(Customer.class))).thenReturn(customer1);
		Customer result=bankingService.depositFunds(1,500);
		assertEquals(1500,result.getBalance());
	}

	@Test
	void testWithdrawl(){
		when(bankingRepository.findById(1)).thenReturn(Optional.of(customer1));
		when(bankingRepository.save(any(Customer.class))).thenReturn(customer1);
		Customer result=bankingService.withdrawlFunds(1,500);
		assertEquals(500,result.getBalance());
	}

	@Test
	void testInsufficientBalance(){
		when(bankingRepository.findById(1)).thenThrow(new WithdrawlAmountGreaterThanBalance("Amount Greater Than Balance"));
		assertThrows(WithdrawlAmountGreaterThanBalance.class,()->{
			bankingService.withdrawlFunds(1,5000);
		});
	}

	@Test
	void transfer(){
		when(bankingRepository.findById(1)).thenReturn(Optional.of(customer1));
		when(bankingRepository.findById(2)).thenReturn(Optional.of(customer2));
		when(bankingRepository.save(customer1)).thenReturn(customer1);
		when(bankingRepository.save(customer2)).thenReturn(customer2);
		bankingService.transferFunds(1,2,500);
		assertEquals(500, customer1.getBalance());
		assertEquals(1500, customer2.getBalance());
	}
}
