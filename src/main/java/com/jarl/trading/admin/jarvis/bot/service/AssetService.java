package com.jarl.trading.admin.jarvis.bot.service;

import java.util.List;

import com.jarl.trading.admin.jarvis.bot.model.dto.AssetDTO;

public interface AssetService {

	public AssetDTO add(AssetDTO request);

	public List<AssetDTO> getAll();

	public List<AssetDTO> getAllByAccount(Long account);

	public AssetDTO getById(Long id);

	public AssetDTO getBySymbolAndAccount(String symbol, Long account);

	public AssetDTO update(AssetDTO request);

	public void delete(Long id);

}
