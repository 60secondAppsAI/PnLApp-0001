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

import com.PnLApp.domain.StrategyPnL;
import com.PnLApp.dto.StrategyPnLDTO;
import com.PnLApp.dto.StrategyPnLSearchDTO;
import com.PnLApp.dto.StrategyPnLPageDTO;
import com.PnLApp.service.StrategyPnLService;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/strategyPnL")
@RestController
public class StrategyPnLController {

	private final static Logger logger = LoggerFactory.getLogger(StrategyPnLController.class);

	@Autowired
	StrategyPnLService strategyPnLService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<StrategyPnL> getAll() {

		List<StrategyPnL> strategyPnLs = strategyPnLService.findAll();
		
		return strategyPnLs;	
	}

	@GetMapping(value = "/{strategyPnLId}")
	@ResponseBody
	public StrategyPnLDTO getStrategyPnL(@PathVariable Integer strategyPnLId) {
		
		return (strategyPnLService.getStrategyPnLDTOById(strategyPnLId));
	}

 	@RequestMapping(value = "/addStrategyPnL", method = RequestMethod.POST)
	public ResponseEntity<?> addStrategyPnL(@RequestBody StrategyPnLDTO strategyPnLDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = strategyPnLService.addStrategyPnL(strategyPnLDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/strategyPnLs")
	public ResponseEntity<StrategyPnLPageDTO> getStrategyPnLs(StrategyPnLSearchDTO strategyPnLSearchDTO) {
 
		return strategyPnLService.getStrategyPnLs(strategyPnLSearchDTO);
	}	

	@RequestMapping(value = "/updateStrategyPnL", method = RequestMethod.POST)
	public ResponseEntity<?> updateStrategyPnL(@RequestBody StrategyPnLDTO strategyPnLDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = strategyPnLService.updateStrategyPnL(strategyPnLDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
