package com.softeam.kata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softeam.kata.entities.Account;
import com.softeam.kata.service.AccountManagementService;

/**
 *
 * @author Louay Saadi
 * 
 */
public class AccountManagementServiceImpl implements AccountManagementService {

	// This list is used to store accounts
	private List<Account> accountList = new ArrayList<Account>();

	public AccountManagementServiceImpl(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public Integer saveMoney(Integer amountToAdd, Integer accountNumber) {
		return 0;
	}
}
