package com.PnLApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseStrategyPnLDTO {

	private Integer ownerId;

	private Integer strategyPnLId;

	private Integer strategyPnLStatus;
	
	private Integer nextOrPrevious;
}

