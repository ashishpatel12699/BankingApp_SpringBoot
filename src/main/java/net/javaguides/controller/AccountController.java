package net.javaguides.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.dto.AccountDto;
import net.javaguides.service.AccountService;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	public AccountController(AccountService accountService)
	{
		this.accountService = accountService;
	}
	
	// Add Account RestApi
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
	{
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping("{/id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
{
	AccountDto accountDto = accountService.getAccountById(id);
	return ResponseEntity.ok(accountDto);
}
	
	//Deposite Rest Api
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposite(@PathVariable Long id,
		@RequestBody	Map<String, Double> request)
	{
		
		Double amount = request.get("amount"); 
		AccountDto accountDto = accountService.deposite(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//withdraw restApi
	
	@PutMapping("/{id}/wihdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
			@RequestBody Map<String, Double> request)
	{
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
		
	}
	//get All Account RestAPI
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAcounts()
	{
		List<AccountDto> accounts =  accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	// Delete Account Api
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id)
	{
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted Sucessfully");
	}
}
