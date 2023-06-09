package com.jarl.trading.admin.jarvis.bot.model.entity;

import java.io.Serializable;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asset implements Serializable {

	private static final long serialVersionUID = 4627575646616837741L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 10, nullable = false)
	private String symbol;
	
	@Column(length = 5, nullable = false)
	private Float lot;
	
	@Column(length = 10, nullable = false)
	private String type;
	
	@Column(length = 2, columnDefinition = "integer default 1")
	private Integer risk;
	
	@Column(length = 2, columnDefinition = "integer default 2")
	private Integer reward;
	
	@Column(length = 7, nullable = false)
	private Integer tfTendence;
	
	@Column(length = 7, nullable = false)
	private Integer tfEntry;
	
	@Column(length = 7, nullable = false)
	private Integer tfConfirmation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false, updatable = false)
	@Fetch(value = FetchMode.JOIN)
	private Account account;

}
