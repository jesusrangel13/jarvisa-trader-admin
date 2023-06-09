package com.jarl.trading.admin.jarvis.bot.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account implements Serializable {

	private static final long serialVersionUID = -5206742235420398216L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, length = 30, nullable = false)
	private String login;

	@Column(length = 60, nullable = false)
	private String traderPassword;

	@Column(length = 60, nullable = false)
	private String investorPassword;

	@Column(length = 100, nullable = false)
	private String server;

}
