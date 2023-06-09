package com.jarl.trading.admin.jarvis.bot.model.dto;

import java.io.Serializable;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO implements Serializable {

	private static final long serialVersionUID = -717184786215946655L;

	private Long id;

	@Nonnull
	private String login;

	@Nonnull
	private String traderPassword;

	private String investorPassword;

}
