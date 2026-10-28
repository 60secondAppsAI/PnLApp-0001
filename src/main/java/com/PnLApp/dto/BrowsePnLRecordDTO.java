package com.PnLApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowsePnLRecordDTO {

	private Integer ownerId;

	private Integer pnLRecordId;

	private Integer pnLRecordStatus;
	
	private Integer nextOrPrevious;
}

