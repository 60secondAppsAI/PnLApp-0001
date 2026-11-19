package com.PnLApp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.PnLApp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.PnLApp.domain.PnLRecord;
import com.PnLApp.dto.PnLRecordDTO;
import com.PnLApp.dto.PnLRecordSearchDTO;
import com.PnLApp.dto.PnLRecordPageDTO;
import com.PnLApp.service.PnLRecordService;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/pnLRecord")
@RestController
public class PnLRecordController {

	private final static Logger logger = LoggerFactory.getLogger(PnLRecordController.class);

	@Autowired
	PnLRecordService pnLRecordService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PnLRecord> getAll() {

		List<PnLRecord> pnLRecords = pnLRecordService.findAll();
		
		return pnLRecords;	
	}

	@GetMapping(value = "/{pnLRecordId}")
	@ResponseBody
	public PnLRecordDTO getPnLRecord(@PathVariable Integer pnLRecordId) {
		
		return (pnLRecordService.getPnLRecordDTOById(pnLRecordId));
	}

 	@RequestMapping(value = "/addPnLRecord", method = RequestMethod.POST)
	public ResponseEntity<?> addPnLRecord(@RequestBody PnLRecordDTO pnLRecordDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = pnLRecordService.addPnLRecord(pnLRecordDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/pnLRecords")
	public ResponseEntity<PnLRecordPageDTO> getPnLRecords(PnLRecordSearchDTO pnLRecordSearchDTO) {
 
		return pnLRecordService.getPnLRecords(pnLRecordSearchDTO);
	}	

	@RequestMapping(value = "/updatePnLRecord", method = RequestMethod.POST)
	public ResponseEntity<?> updatePnLRecord(@RequestBody PnLRecordDTO pnLRecordDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = pnLRecordService.updatePnLRecord(pnLRecordDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
