package com.jarl.trading.admin.jarvis.bot.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.jarl.trading.admin.jarvis.bot.model.dto.AccountDTO;
import com.jarl.trading.admin.jarvis.bot.service.AccountService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/account")
public class AccountController {

	@Autowired
	private AccountService service;

	@GetMapping()
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		log.info("START service getAllAccounts");
		List<AccountDTO> response = service.getAll();
		log.info("END service getAllAccounts");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
		log.info("START service getAccount by id: {}", id);

		if (id == null) {
			throw new IllegalArgumentException("field Id is mandatory");
		}

		AccountDTO response = service.getById(id);
		log.info("END service getAccount by id: {}", id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/login/{login}")
	public ResponseEntity<AccountDTO> getAccountByLogin(@PathVariable String login) {
		log.info("START service getAccount by login: {}", login);

		if (login == null) {
			throw new IllegalArgumentException("field login is mandatory");
		}

		AccountDTO response = service.getByLogin(login);
		log.info("END service getAccount by id: {}", login);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO request) {
		log.info("START service add new account: {}", request);
		AccountDTO response = service.add(request);
		log.info("END service add new account");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<AccountDTO> updateAccount(@RequestBody @Valid AccountDTO request) {
		log.info("START service to update and existin account: {}", request);

		if (request.getId() == null) {
			throw new IllegalArgumentException("field Id is mandatory");
		}

		AccountDTO response = service.update(request);

		if (response == null) {
			throw new NoSuchElementException("Dont exist account");
		}

		log.info("END service to update and existin account");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<AccountDTO> deleteAccount(@PathVariable Long id) {
		log.info("START service delete existing account: {}", id);

		if (id == null) {
			throw new IllegalArgumentException("field Id is mandatory");
		}

		service.delete(id);
		log.info("END service delete existing account");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
