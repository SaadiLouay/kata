package com.softeam.kata.entities;

import java.math.BigDecimal;

/**
*
* @author Louay Saadi
* 
*/

/**
 * Entity class for Account
 */
public class Account {

	// Number identity of the account
	private Integer accountNumber;
	
	// Amount available in the account
	private BigDecimal amount;
	
	
	public Account(Integer accountNumber, BigDecimal amount) {
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
