package com.PnLApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowsePnLAdjustmentDTO {

	private Integer ownerId;

	private Integer pnLAdjustmentId;

	private Integer pnLAdjustmentStatus;
	
	private Integer nextOrPrevious;
}

