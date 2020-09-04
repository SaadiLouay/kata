package com.softeam.kata.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.softeam.kata.entities.Account;
import com.softeam.kata.entities.Operation;
import com.softeam.kata.exceptions.OverdraftWithdrawalException;
import com.softeam.kata.service.impl.AccountManagementServiceImpl;

public class AccountManagementServiceTest {
	AccountManagementServiceImpl accountManagement;

	@Before
	public void setUp() throws Exception {
		Account account1 = new Account(123, new BigDecimal(0));
		Account account2 = new Account(456, new BigDecimal(0));
		Account account3 = new Account(789, new BigDecimal(0));
		Account account4 = new Account(245, new BigDecimal(0));
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

		BigDecimal amountToAdd = new BigDecimal(250);
		Integer accountNumber = 123;
		// Get the account amount before saving money
		BigDecimal beforeAmount = accountManagement.getAccountList()
				.stream()
				.filter(a -> a.getAccountNumber() == accountNumber)
				.findFirst()
				.get().getAmount();
		BigDecimal afterAmount = accountManagement.saveMoney(amountToAdd, accountNumber);
		BigDecimal exectedAfterAmount = beforeAmount.add(amountToAdd);
		assertEquals(afterAmount, exectedAfterAmount);
	}
	
	@Test
	public void testRetrieveMoney() {

		BigDecimal amountToAdd = new BigDecimal(250);
		BigDecimal amountToRetrieve = new BigDecimal(100);
		Integer accountNumber = 456;
		BigDecimal afterAddAmount = accountManagement.saveMoney(amountToAdd, accountNumber);
		BigDecimal afterRetrieveAmmount = accountManagement.retrieveMoney(amountToRetrieve, accountNumber);
		BigDecimal exectedAfterAmount = afterAddAmount.subtract(amountToRetrieve);
		assertEquals(afterRetrieveAmmount, exectedAfterAmount);
	}
	
	@Test(expected = OverdraftWithdrawalException.class)
	public void testRetrieveMoneyOverdraft() {

		BigDecimal amountToAdd = new BigDecimal(50);
		BigDecimal amountToRetrieve = new BigDecimal(100);
		Integer accountNumber = 123;
		BigDecimal afterAddAmount = accountManagement.saveMoney(amountToAdd, accountNumber);
		BigDecimal afterRetrieveAmmount = accountManagement.retrieveMoney(amountToRetrieve, accountNumber);
		BigDecimal exectedAfterAmount = afterAddAmount.subtract(amountToRetrieve);
		assertEquals(afterRetrieveAmmount, exectedAfterAmount);
	}
	
	@Test
	public void testSeeAccountHistory() {

		BigDecimal amountToAdd = new BigDecimal(250);
		BigDecimal amountToRetrieve = new BigDecimal(100);
		Integer accountNumber = 245;
		BigDecimal afterAddAmount = accountManagement.saveMoney(amountToAdd, accountNumber);
		BigDecimal afterRetrieveAmmount = accountManagement.retrieveMoney(amountToRetrieve, accountNumber);

		String history = accountManagement.seeAccountHistory(accountNumber);
		
		assertFalse(history.isEmpty());
		assertFalse(history.equals("No History Available"));

		System.out.println(history); // Just to display the history in the console and see it
	}
}