package net.javaguides.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.dto.AccountDto;
import net.javaguides.entity.Account;
import net.javaguides.mapper.AccountMapper;
import net.javaguides.repository.AccountRepository;
import net.javaguides.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAcount  = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAcount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		Account account =accountRepository.findById(id)
		.orElseThrow( () -> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposite(Long id, double amount) {
		Account account =accountRepository.findById(id)
		.orElseThrow( () -> new RuntimeException("Account does not exists"));
		double total = account.getBalance() + amount;
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account =accountRepository.findById(id)
		.orElseThrow( () -> new RuntimeException("Account does not exists"));
		if(account.getBalance() < amount)
		{
			throw new RuntimeException("Insuffient amount");
		}
		double total= account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
			List<Account> accounts= accountRepository.findAll();
			return accounts .stream().map((account) -> AccountMapper.mapToAccountDto(account))
			.collect(Collectors.toList());
		
		}


	@Override
	public void deleteAccount(Long id) {
		Account account =accountRepository.findById(id)
		.orElseThrow( () -> new RuntimeException("Account does not exists"));

		accountRepository.deleteById(id);
		
	}
	}




