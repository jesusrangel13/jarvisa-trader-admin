package com.jarl.trading.admin.jarvis.bot.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarl.trading.admin.jarvis.bot.model.entity.Asset;

public interface AssetDAO extends JpaRepository<Asset, Long>{

}
