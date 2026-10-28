package com.PnLApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StrategyPnLSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer strategyPnLId;
	
	private Integer pnlRecordId;
	
	private Integer strategyId;
	
	private Double realizedPnL;
	
	private Double unrealizedPnL;
	
}
