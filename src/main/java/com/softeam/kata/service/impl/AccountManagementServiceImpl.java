package com.softeam.kata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softeam.kata.entities.Account;
import com.softeam.kata.exceptions.OverdraftWithdrawalException;
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
		Integer currentAmount = getAccountWithNumber(accountNumber).getAmount();
		
		getAccountWithNumber(accountNumber).setAmount(currentAmount + amountToAdd);
		
		return getAccountWithNumber(accountNumber).getAmount();
	}


	@Override
	public Integer retrieveMoney(Integer amountToRetrieve, Integer accountNumber) {
		Integer currentAmount = getAccountWithNumber(accountNumber).getAmount();
		
		if(amountToRetrieve > currentAmount) throw new OverdraftWithdrawalException("Unpermitted overdraft withdrawal");
		getAccountWithNumber(accountNumber).setAmount(currentAmount - amountToRetrieve);
		
		return getAccountWithNumber(accountNumber).getAmount();
	}
	
	private Account getAccountWithNumber(Integer accountNumber) {
		return this.getAccountList()
				.stream()
				.filter(a -> a.getAccountNumber() == accountNumber)
				.findAny()
				.get();
	}
}
