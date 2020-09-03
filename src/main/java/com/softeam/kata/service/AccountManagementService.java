package com.softeam.kata.service;

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
	public Integer saveMoney(Integer amountToAdd, Integer accountNumber);
	
	
	/**
	 * Takes an "amountToRetrieve" parameter and a "accountNumber" parameter and returns 
	 * an Integeer , make a withdrawal from the account.
	 * <p>
	 *
	 * @return the updated amount of the account
	 */
	public Integer retrieveMoney(Integer amountToRetrieve, Integer accountNumber);
}