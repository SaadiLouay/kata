package com.softeam.kata.service;

import java.math.BigDecimal;
import java.util.List;

import com.softeam.kata.entities.Operation;

/**
*
* @author Louay Saadi
* 
*/
public interface AccountManagementService {
	
	/**
	 * Takes an "amountToAdd" parameter and a "accountNumber" parameter and returns 
	 * an Integeer , make a deposit in account.
	 * <p>
	 *
	 * @return the updated amount of the account
	 */
	public BigDecimal saveMoney(BigDecimal amountToAdd, Integer accountNumber);
	
	
	/**
	 * Takes an "amountToRetrieve" parameter and a "accountNumber" parameter and returns 
	 * an Integeer , make a withdrawal from the account.
	 * <p>
	 *
	 * @return the updated amount of the account
	 */
	public BigDecimal retrieveMoney(BigDecimal amountToRetrieve, Integer accountNumber);
	
	/**
	 * Takes an "accountNumber" parameter and returns 
	 * an list of operations, return the history of the account.
	 * <p>
	 *
	 * @return the list of operations
	 */
	public String seeAccountHistory(Integer accountNumber);
}