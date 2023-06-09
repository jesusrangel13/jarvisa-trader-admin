package com.jarl.trading.admin.jarvis.bot.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarl.trading.admin.jarvis.bot.model.entity.Account;
import com.jarl.trading.admin.jarvis.bot.model.entity.Asset;

public interface AssetDAO extends JpaRepository<Asset, Long> {

	Asset findBySymbolAndAccount(String symbol, Account account);

	List<Asset> findByAccount(Account account);
}
