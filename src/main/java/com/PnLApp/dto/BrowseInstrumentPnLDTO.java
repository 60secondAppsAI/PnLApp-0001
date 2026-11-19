package com.PnLApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseInstrumentPnLDTO {

	private Integer ownerId;

	private Integer instrumentPnLId;

	private Integer instrumentPnLStatus;
	
	private Integer nextOrPrevious;
}

