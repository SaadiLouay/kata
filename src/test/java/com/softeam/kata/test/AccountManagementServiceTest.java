package com.softeam.kata.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.softeam.kata.entities.Account;
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
		accountManagement = new AccountManagementServiceImpl(accountList);
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
}