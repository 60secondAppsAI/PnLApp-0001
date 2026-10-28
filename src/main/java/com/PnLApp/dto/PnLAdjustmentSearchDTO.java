package com.PnLApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PnLAdjustmentSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer pnlAdjustmentId;
	
	private Integer pnlRecordId;
	
	private String adjustmentType;
	
	private Double amount;
	
	private String description;
	
	private Integer pnLAdjustmentId;
	
}
