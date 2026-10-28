package com.PnLApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PnLRecordSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer pnlRecordId;
	
	private Integer portfolioId;
	
	private Date calculationDate;
	
	private Double realizedPnL;
	
	private Double unrealizedPnL;
	
	private Double totalPnL;
	
	private Integer pnLRecordId;
	
}
