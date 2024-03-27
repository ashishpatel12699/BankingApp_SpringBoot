package net.javaguides.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
