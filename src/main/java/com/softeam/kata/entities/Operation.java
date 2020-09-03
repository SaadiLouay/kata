package com.softeam.kata.entities;

import java.util.Date;

public class Operation {

	private Integer accountNumber;
	private OperationType type;
	private Date date;
	private Integer amount;
	private Integer balance;

	

	public Operation(Integer accountNumber, OperationType type, Date date, Integer amount, Integer balance) {
		this.accountNumber = accountNumber;
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

}
