package com.softeam.kata.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.softeam.kata.entities.Account;
import com.softeam.kata.entities.Operation;
import com.softeam.kata.entities.OperationType;
import com.softeam.kata.exceptions.OverdraftWithdrawalException;
import com.softeam.kata.service.AccountManagementService;

/**
 *
 * @author Louay Saadi
 * 
 */
public class AccountManagementServiceImpl implements AccountManagementService {

	// This list is used to store accounts
	private List<Account> accountList;
	
	// This list is used to store operations
	private List<Operation> operationList;

	public AccountManagementServiceImpl(List<Account> accountList, List<Operation> operationList) {
		this.accountList = accountList;
		this.operationList = operationList;
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
		
		Integer balance = getAccountWithNumber(accountNumber).getAmount();
		
		Date date = new Date(System.currentTimeMillis());
		operationList.add(new Operation(accountNumber, OperationType.DEPOSIT,date, amountToAdd, balance));
		
		return balance;
	}


	@Override
	public Integer retrieveMoney(Integer amountToRetrieve, Integer accountNumber) {
		Integer currentAmount = getAccountWithNumber(accountNumber).getAmount();
		
		if(amountToRetrieve > currentAmount) throw new OverdraftWithdrawalException("Unpermitted overdraft withdrawal");
		getAccountWithNumber(accountNumber).setAmount(currentAmount - amountToRetrieve);
		
		Integer balance = getAccountWithNumber(accountNumber).getAmount();
		
		Date date = new Date(System.currentTimeMillis());
		operationList.add(new Operation(accountNumber, OperationType.WITHDRAWAL,date, amountToRetrieve, balance));
		
		return balance;
	}
	
	private Account getAccountWithNumber(Integer accountNumber) {
		return accountList
				.stream()
				.filter(a -> a.getAccountNumber() == accountNumber)
				.findAny()
				.get();
	}

	@Override
	public List<Operation> seeAccountHistory(Integer accountNumber) {
		
		List<Operation> history = new ArrayList<Operation>();
		for(Operation operation : operationList) {
			if (operation.getAccountNumber() == accountNumber)
				history.add(operation);
		}
		return history;
	}
}
