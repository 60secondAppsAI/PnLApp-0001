package com.PnLApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class StrategyPnLDTO {

	private Integer strategyPnLId;

	private Integer pnlRecordId;

	private Integer strategyId;

	private Double realizedPnL;

	private Double unrealizedPnL;






}
