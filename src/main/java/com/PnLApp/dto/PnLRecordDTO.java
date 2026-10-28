package com.PnLApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PnLRecordDTO {

	private Integer pnlRecordId;

	private Integer portfolioId;

	private Date calculationDate;

	private Double realizedPnL;

	private Double unrealizedPnL;

	private Double totalPnL;

	private Integer pnLRecordId;






}
