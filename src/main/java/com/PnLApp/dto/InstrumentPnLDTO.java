package com.PnLApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class InstrumentPnLDTO {

	private Integer instrumentPnLId;

	private Integer pnlRecordId;

	private Integer instrumentId;

	private Double realizedPnL;

	private Double unrealizedPnL;






}
