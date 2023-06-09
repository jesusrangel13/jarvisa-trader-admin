package com.jarl.trading.admin.jarvis.bot.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarl.trading.admin.jarvis.bot.model.entity.Account;

public interface AccountDAO extends JpaRepository<Account, Long> {
	
	Account findByLogin(String login);

}
