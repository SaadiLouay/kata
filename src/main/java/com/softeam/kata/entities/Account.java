package com.softeam.kata.entities;

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
	private Integer amount;
	
	
	public Account(Integer accountNumber, Integer amount) {
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
