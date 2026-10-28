package com.PnLApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.PnLApp.domain.PnLAdjustment;
import com.PnLApp.dto.PnLAdjustmentDTO;
import com.PnLApp.dto.PnLAdjustmentSearchDTO;
import com.PnLApp.dto.PnLAdjustmentPageDTO;
import com.PnLApp.dto.PnLAdjustmentConvertCriteriaDTO;
import com.PnLApp.service.GenericService;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PnLAdjustmentService extends GenericService<PnLAdjustment, Integer> {

	List<PnLAdjustment> findAll();

	ResultDTO addPnLAdjustment(PnLAdjustmentDTO pnLAdjustmentDTO, RequestDTO requestDTO);

	ResultDTO updatePnLAdjustment(PnLAdjustmentDTO pnLAdjustmentDTO, RequestDTO requestDTO);

    Page<PnLAdjustment> getAllPnLAdjustments(Pageable pageable);

    Page<PnLAdjustment> getAllPnLAdjustments(Specification<PnLAdjustment> spec, Pageable pageable);

	ResponseEntity<PnLAdjustmentPageDTO> getPnLAdjustments(PnLAdjustmentSearchDTO pnLAdjustmentSearchDTO);
	
	List<PnLAdjustmentDTO> convertPnLAdjustmentsToPnLAdjustmentDTOs(List<PnLAdjustment> pnLAdjustments, PnLAdjustmentConvertCriteriaDTO convertCriteria);

	PnLAdjustmentDTO getPnLAdjustmentDTOById(Integer pnLAdjustmentId);



	
}
