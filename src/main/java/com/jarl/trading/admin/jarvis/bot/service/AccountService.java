package com.jarl.trading.admin.jarvis.bot.service;

import java.util.List;

import com.jarl.trading.admin.jarvis.bot.model.dto.AccountDTO;

public interface AccountService {

	public AccountDTO add(AccountDTO request);

	public List<AccountDTO> getAll();

	public AccountDTO getById(Long id);

	public AccountDTO getByLogin(String login);

	public AccountDTO update(AccountDTO request);

	public void delete(Long id);

}
