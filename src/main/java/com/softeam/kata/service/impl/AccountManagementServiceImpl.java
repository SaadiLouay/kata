package com.softeam.kata.service.impl;

import java.math.BigDecimal;
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

	@Override
	public BigDecimal saveMoney(BigDecimal amountToAdd, Integer accountNumber) {
		BigDecimal currentAmount = getAccountWithNumber(accountNumber).getAmount();
		
		getAccountWithNumber(accountNumber).setAmount(currentAmount.add(amountToAdd));
		
		BigDecimal balance = getAccountWithNumber(accountNumber).getAmount();
		
		Date date = new Date(System.currentTimeMillis());
		operationList.add(new Operation(accountNumber, OperationType.DEPOSIT, date, amountToAdd, balance));
		
		return balance;
	}


	@Override
	public BigDecimal retrieveMoney(BigDecimal amountToRetrieve, Integer accountNumber) {
		BigDecimal currentAmount = getAccountWithNumber(accountNumber).getAmount();
		
		if(amountToRetrieve.compareTo(currentAmount) == 1) throw new OverdraftWithdrawalException("Unpermitted overdraft withdrawal");
		getAccountWithNumber(accountNumber).setAmount(currentAmount.subtract(amountToRetrieve));
		
		BigDecimal balance = getAccountWithNumber(accountNumber).getAmount();
		
		Date date = new Date(System.currentTimeMillis());
		operationList.add(new Operation(accountNumber, OperationType.WITHDRAWAL, date, amountToRetrieve, balance));
		
		return balance;
	}
	
	private Account getAccountWithNumber(Integer accountNumber) {
		return accountList
				.stream()
				.filter(a -> a.getAccountNumber().equals(accountNumber))
				.findAny()
				.get();
	}

	@Override
	public String seeAccountHistory(Integer accountNumber) {
		String history;
		
		List<Operation> operations = new ArrayList<Operation>();
		for(Operation operation : operationList) {
			if (operation.getAccountNumber().equals(accountNumber))
				operations.add(operation);
		}
		if (operations.isEmpty() == false) {
			 history = "Account History: Account number " 
                     + accountNumber + "\nBalance: " 
			         + getAccountWithNumber(accountNumber).getAmount() + "\n\n"
			         + "History:\n";
				for(Operation operation : operations) {
					history = history + operation.getDate() + " " 
				                      + operation.getType().name() + " "
				                      + operation.getAmount() + "\n";
				}
		} else history = "No History Available";

		return history;
	}
}