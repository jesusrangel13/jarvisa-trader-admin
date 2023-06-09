package com.jarl.trading.admin.jarvis.bot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jarl.trading.admin.jarvis.bot.model.dao.AssetDAO;
import com.jarl.trading.admin.jarvis.bot.model.dto.AssetDTO;
import com.jarl.trading.admin.jarvis.bot.model.entity.Account;
import com.jarl.trading.admin.jarvis.bot.model.entity.Asset;
import com.jarl.trading.admin.jarvis.bot.service.AssetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetDAO assetDAO;

	@Override
	public AssetDTO add(AssetDTO request) {
		log.info("START process to add asset with parameters {}", request);

		// Map requestDTO to Entity
		Asset asset = DtoToEntity(request);
		asset = assetDAO.save(asset);

		// Map entity to dto to respond
		log.info("END process to add asset with parameters {}", asset);
		return EntityToDto(asset);
	}

	@Override
	public List<AssetDTO> getAll() {
		log.info("START process find all assets");
		// Define object for exit
		List<AssetDTO> response = new ArrayList<>();

		// Find all account
		List<Asset> assets = assetDAO.findAll();

		// Map response to DTO
		assets.stream().forEach(asset -> {
			response.add(EntityToDto(asset));
		});

		log.info("END process find all assets");
		return response;
	}

	@Override
	public List<AssetDTO> getAllByAccount(Long account) {
		log.info("START process to find assets by account {}", account);

		// Define object for exit
		List<AssetDTO> response = new ArrayList<>();

		List<Asset> assets = assetDAO.findByAccount(Account.builder().id(account).build());

		// Map response to DTO
		assets.stream().forEach(asset -> {
			response.add(EntityToDto(asset));
		});

		log.info("END process to find asset by account");
		return response;
	}

	@Override
	public AssetDTO getById(Long id) {
		log.info("START process to find asset by id {}", id);

		Asset asset = assetDAO.findById(id).orElse(null);

		log.info("END process to find asset by id");
		return EntityToDto(asset);
	}

	@Override
	public AssetDTO getBySymbolAndAccount(String symbol, Long account) {
		log.info("START process to find asset by symbol: {} from the account: {}", symbol, account);

		Asset asset = assetDAO.findBySymbolAndAccount(symbol, Account.builder().id(account).build());

		log.info("END process to find asset by symbol and account");
		return EntityToDto(asset);
	}

	@Override
	public AssetDTO update(AssetDTO request) {
		log.info("START process to update asset with parameters {}", request);

		Asset bdAsset = assetDAO.findById(request.getId()).orElse(null);

		if (bdAsset != null) {
			// Map requestDTO to Entity
			Asset asset = DtoToEntity(request);
			asset.setAccount(bdAsset.getAccount());
			asset = assetDAO.save(asset);

			// Map entity to dto to respond
			log.info("END process to update asset with parameters {}", asset);
			return EntityToDto(asset);
		} else {
			return null;
		}
	}

	@Override
	public void delete(Long id) {
		log.info("START process to delete asset by id: {}", id);

		assetDAO.deleteById(id);

		log.info("END process to delete asset by id: {}", id);

	}

	public static AssetDTO EntityToDto(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		AssetDTO accountDTO = mapper.convertValue(object, AssetDTO.class);
		return accountDTO;
	}

	public static Asset DtoToEntity(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Asset account = mapper.convertValue(object, Asset.class);
		return account;
	}

}
