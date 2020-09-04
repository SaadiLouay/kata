package com.softeam.kata.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Operation {

	private Integer accountNumber;
	private OperationType type;
	private Date date;
	private BigDecimal amount;
	private BigDecimal balance;

	

	public Operation(Integer accountNumber, OperationType type, Date date, BigDecimal amount, BigDecimal balance) {
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

}
