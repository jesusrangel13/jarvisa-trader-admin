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

import com.jarl.trading.admin.jarvis.bot.model.dto.AssetDTO;
import com.jarl.trading.admin.jarvis.bot.service.AssetService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/asset")
public class AssetController {

	@Autowired
	private AssetService service;

	@GetMapping()
	public ResponseEntity<List<AssetDTO>> getAllAssets() {
		log.info("START service getAllAssets");
		List<AssetDTO> response = service.getAll();
		log.info("END service getAllAccounts");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<AssetDTO> getAssetById(@PathVariable Long id) {
		log.info("START service getAsset by id: {}", id);

		if (id == null) {
			throw new IllegalArgumentException("field Id is mandatory");
		}

		AssetDTO response = service.getById(id);
		log.info("END service getAccount by id: {}", id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/account/{accountId}")
	public ResponseEntity<List<AssetDTO>> getAssetsByAccount(@PathVariable Long accountId) {
		log.info("START service get all assets by account: {}", accountId);

		if (accountId == null) {
			throw new IllegalArgumentException("field Id is mandatory");
		}

		List<AssetDTO> response = service.getAllByAccount(accountId);
		log.info("END service get all assets by account");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/symbol/{symbol}/account/{accountId}")
	public ResponseEntity<AssetDTO> getBySymbolAndAccount(@PathVariable String symbol, @PathVariable Long accountId) {
		log.info("START service get asset by symbol: {} and account: {}", symbol, accountId);

		if (symbol == null || accountId == null) {
			throw new IllegalArgumentException("field symbol and accountId are mandatory");
		}

		AssetDTO response = service.getBySymbolAndAccount(symbol, accountId);
		log.info("END service get asset by symbol: {} and account");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<AssetDTO> addAsset(@RequestBody AssetDTO request) {
		log.info("START service add new asset: {}", request);
		AssetDTO response = service.add(request);
		log.info("END service add new asset");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<AssetDTO> updateAccount(@RequestBody @Valid AssetDTO request) {
		log.info("START service to update and existing asset: {}", request);

		if (request.getId() == null) {
			throw new IllegalArgumentException("field Id is mandatory");
		}

		AssetDTO response = service.update(request);

		if (response == null) {
			throw new NoSuchElementException("Dont exist account");
		}

		log.info("END sservice to update and existing assett");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<AssetDTO> deleteAccount(@PathVariable Long id) {
		log.info("START service delete existing asset: {}", id);

		if (id == null) {
			throw new IllegalArgumentException("field Id is mandatory");
		}

		service.delete(id);
		log.info("END service delete existing asset");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
