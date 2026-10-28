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

import com.PnLApp.domain.InstrumentPnL;
import com.PnLApp.dto.InstrumentPnLDTO;
import com.PnLApp.dto.InstrumentPnLSearchDTO;
import com.PnLApp.dto.InstrumentPnLPageDTO;
import com.PnLApp.service.InstrumentPnLService;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/instrumentPnL")
@RestController
public class InstrumentPnLController {

	private final static Logger logger = LoggerFactory.getLogger(InstrumentPnLController.class);

	@Autowired
	InstrumentPnLService instrumentPnLService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<InstrumentPnL> getAll() {

		List<InstrumentPnL> instrumentPnLs = instrumentPnLService.findAll();
		
		return instrumentPnLs;	
	}

	@GetMapping(value = "/{instrumentPnLId}")
	@ResponseBody
	public InstrumentPnLDTO getInstrumentPnL(@PathVariable Integer instrumentPnLId) {
		
		return (instrumentPnLService.getInstrumentPnLDTOById(instrumentPnLId));
	}

 	@RequestMapping(value = "/addInstrumentPnL", method = RequestMethod.POST)
	public ResponseEntity<?> addInstrumentPnL(@RequestBody InstrumentPnLDTO instrumentPnLDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = instrumentPnLService.addInstrumentPnL(instrumentPnLDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/instrumentPnLs")
	public ResponseEntity<InstrumentPnLPageDTO> getInstrumentPnLs(InstrumentPnLSearchDTO instrumentPnLSearchDTO) {
 
		return instrumentPnLService.getInstrumentPnLs(instrumentPnLSearchDTO);
	}	

	@RequestMapping(value = "/updateInstrumentPnL", method = RequestMethod.POST)
	public ResponseEntity<?> updateInstrumentPnL(@RequestBody InstrumentPnLDTO instrumentPnLDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = instrumentPnLService.updateInstrumentPnL(instrumentPnLDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
