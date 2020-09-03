package com.softeam.kata.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.softeam.kata.entities.Account;
import com.softeam.kata.entities.Operation;
import com.softeam.kata.entities.OperationType;
import com.softeam.kata.exceptions.OverdraftWithdrawalException;
import com.softeam.kata.service.impl.AccountManagementServiceImpl;

public class AccountManagementServiceTest {
	AccountManagementServiceImpl accountManagement;

	@Before
	public void setUp() throws Exception {
		Account account1 = new Account(123, 0);
		Account account2 = new Account(456, 0);
		Account account3 = new Account(789, 0);
		Account account4 = new Account(245, 0);
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(account1);
		accountList.add(account2);
		accountList.add(account3);
		accountList.add(account4);
		
		List<Operation> operationList = new ArrayList<Operation>();
		accountManagement = new AccountManagementServiceImpl(accountList, operationList);
	}

	@Test
	public void testSaveMoney() {

		Integer amountToAdd = 250;
		Integer accountNumber = 123;
		// Get the account amount before saving money
		Integer beforeAmount = accountManagement.getAccountList()
				.stream()
				.filter(a -> a.getAccountNumber() == accountNumber)
				.findFirst()
				.get().getAmount();
		Integer afterAmount = accountManagement.saveMoney(amountToAdd, accountNumber);
		Integer exectedAfterAmount = beforeAmount + amountToAdd;
		assertEquals(afterAmount, exectedAfterAmount);
	}
	
	@Test
	public void testRetrieveMoney() {

		Integer amountToAdd = 250;
		Integer amountToRetrieve = 100;
		Integer accountNumber = 123;
		Integer afterAddAmount = accountManagement.saveMoney(amountToAdd, accountNumber);
		Integer afterRetrieveAmmount = accountManagement.retrieveMoney(amountToRetrieve, accountNumber);
		Integer exectedAfterAmount = afterAddAmount - amountToRetrieve;
		assertEquals(afterRetrieveAmmount, exectedAfterAmount);
	}
	
	@Test(expected = OverdraftWithdrawalException.class)
	public void testRetrieveMoneyOverdraft() {

		Integer amountToAdd = 50;
		Integer amountToRetrieve = 100;
		Integer accountNumber = 123;
		Integer afterAddAmount = accountManagement.saveMoney(amountToAdd, accountNumber);
		Integer afterRetrieveAmmount = accountManagement.retrieveMoney(amountToRetrieve, accountNumber);
		Integer exectedAfterAmount = afterAddAmount - amountToRetrieve;
		assertEquals(afterRetrieveAmmount, exectedAfterAmount);
	}
	
	@Test
	public void testSeeAccountHistory() {

		Integer amountToAdd = 250;
		Integer amountToRetrieve = 100;
		Integer accountNumber = 123;
		Integer afterAddAmount = accountManagement.saveMoney(amountToAdd, accountNumber);
		Integer afterRetrieveAmmount = accountManagement.retrieveMoney(amountToRetrieve, accountNumber);

		List<Operation> operations = new ArrayList<Operation>();
		operations = accountManagement.seeAccountHistory(accountNumber);
		
		assertFalse(operations.isEmpty());
		assertTrue(operations.size() == 2);
		assertEquals(OperationType.DEPOSIT, operations.get(0).getType());
		assertEquals(new Integer(250), operations.get(0).getBalance());
		assertEquals(OperationType.WITHDRAWAL, operations.get(1).getType());
		assertEquals(new Integer(150), operations.get(1).getBalance());
	}
	
}