package com.jarl.trading.admin.jarvis.bot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarl.trading.admin.jarvis.bot.model.dao.AccountDAO;
import com.jarl.trading.admin.jarvis.bot.model.dto.AccountDTO;
import com.jarl.trading.admin.jarvis.bot.model.entity.Account;
import com.jarl.trading.admin.jarvis.bot.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	@Override
	public AccountDTO add(AccountDTO request) {
		log.info("START process to add account with parameters {}", request);

		// Map requestDTO to Entity
		Account account = DtoToEntity(request);
		account = accountDAO.save(account);

		// Map entity to dto to respond
		log.info("END process to add account with parameters {}", account);
		return EntityToDto(account);
	}

	@Override
	public List<AccountDTO> getAll() {
		log.info("START process find all accounts");
		// Define object for exit
		List<AccountDTO> response = new ArrayList<>();

		// Find all account
		List<Account> accounts = accountDAO.findAll();

		// Map response to DTO
		accounts.stream().forEach(account -> {
			response.add(EntityToDto(account));
		});

		log.info("END process find all accounts");
		return response;
	}

	@Override
	public AccountDTO getById(Long id) {
		log.info("START process to find account by id: {}", id);
		Account account = accountDAO.findById(id).orElse(null);

		log.info("END process to find account by id: {}", id);
		return EntityToDto(account);
	}

	@Override
	public AccountDTO getByLogin(String login) {
		log.info("START process to find account by login: {}", login);
		Account account = accountDAO.findByLogin(login);

		log.info("END process to find account by login: {}", login);
		return EntityToDto(account);

	}

	@Override
	public AccountDTO update(AccountDTO request) {
		log.info("START process to add account with parameters {}", request);

		// Map requestDTO to Entity
		Account account = DtoToEntity(request);
		account = accountDAO.save(account);

		//TODO Validar si el id que viene existe sino enviar un mensaje de error
		
		// Map entity to dto to respond
		log.info("END process to add account with parameters {}", account);
		return EntityToDto(account);
	}

	@Override
	public void delete(Long id) {
		log.info("START process to delete account by id: {}", id);
		accountDAO.deleteById(id);

		log.info("END process to find account by id: {}", id);

	}

	public static AccountDTO EntityToDto(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		AccountDTO accountDTO = mapper.convertValue(object, AccountDTO.class);
		return accountDTO;
	}

	public static Account DtoToEntity(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Account account = mapper.convertValue(object, Account.class);
		return account;
	}

}
