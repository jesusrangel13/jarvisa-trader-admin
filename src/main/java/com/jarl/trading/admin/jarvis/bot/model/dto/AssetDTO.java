package com.jarl.trading.admin.jarvis.bot.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetDTO implements Serializable {

	private static final long serialVersionUID = 7774924919361335966L;

	private Long id;

	private String symbol;

	private Float lot;

	private String type;

	private Integer risk;

	private Integer reward;

	private Integer tfTendence;

	private Integer tfEntry;

	private Integer tfConfirmation;

	private AccountDTO account;

}