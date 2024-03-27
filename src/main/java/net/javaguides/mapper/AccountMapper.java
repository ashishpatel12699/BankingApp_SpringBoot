package net.javaguides.mapper;

import net.javaguides.dto.AccountDto;
import net.javaguides.entity.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account(
				accountDto.getId(),
				accountDto.getBalance(),
				accountDto.getAccountHolderName()
				
				);
		return account;
	}
	public static AccountDto mapToAccountDto(Account account)
	{
		AccountDto accountDto = new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				
				);
		return accountDto;
	}
	
}
