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

import com.PnLApp.domain.PnLAdjustment;
import com.PnLApp.dto.PnLAdjustmentDTO;
import com.PnLApp.dto.PnLAdjustmentSearchDTO;
import com.PnLApp.dto.PnLAdjustmentPageDTO;
import com.PnLApp.service.PnLAdjustmentService;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/pnLAdjustment")
@RestController
public class PnLAdjustmentController {

	private final static Logger logger = LoggerFactory.getLogger(PnLAdjustmentController.class);

	@Autowired
	PnLAdjustmentService pnLAdjustmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PnLAdjustment> getAll() {

		List<PnLAdjustment> pnLAdjustments = pnLAdjustmentService.findAll();
		
		return pnLAdjustments;	
	}

	@GetMapping(value = "/{pnLAdjustmentId}")
	@ResponseBody
	public PnLAdjustmentDTO getPnLAdjustment(@PathVariable Integer pnLAdjustmentId) {
		
		return (pnLAdjustmentService.getPnLAdjustmentDTOById(pnLAdjustmentId));
	}

 	@RequestMapping(value = "/addPnLAdjustment", method = RequestMethod.POST)
	public ResponseEntity<?> addPnLAdjustment(@RequestBody PnLAdjustmentDTO pnLAdjustmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = pnLAdjustmentService.addPnLAdjustment(pnLAdjustmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/pnLAdjustments")
	public ResponseEntity<PnLAdjustmentPageDTO> getPnLAdjustments(PnLAdjustmentSearchDTO pnLAdjustmentSearchDTO) {
 
		return pnLAdjustmentService.getPnLAdjustments(pnLAdjustmentSearchDTO);
	}	

	@RequestMapping(value = "/updatePnLAdjustment", method = RequestMethod.POST)
	public ResponseEntity<?> updatePnLAdjustment(@RequestBody PnLAdjustmentDTO pnLAdjustmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = pnLAdjustmentService.updatePnLAdjustment(pnLAdjustmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
