package com.PnLApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.PnLApp.domain.InstrumentPnL;
import com.PnLApp.dto.InstrumentPnLDTO;
import com.PnLApp.dto.InstrumentPnLSearchDTO;
import com.PnLApp.dto.InstrumentPnLPageDTO;
import com.PnLApp.dto.InstrumentPnLConvertCriteriaDTO;
import com.PnLApp.service.GenericService;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InstrumentPnLService extends GenericService<InstrumentPnL, Integer> {

	List<InstrumentPnL> findAll();

	ResultDTO addInstrumentPnL(InstrumentPnLDTO instrumentPnLDTO, RequestDTO requestDTO);

	ResultDTO updateInstrumentPnL(InstrumentPnLDTO instrumentPnLDTO, RequestDTO requestDTO);

    Page<InstrumentPnL> getAllInstrumentPnLs(Pageable pageable);

    Page<InstrumentPnL> getAllInstrumentPnLs(Specification<InstrumentPnL> spec, Pageable pageable);

	ResponseEntity<InstrumentPnLPageDTO> getInstrumentPnLs(InstrumentPnLSearchDTO instrumentPnLSearchDTO);
	
	List<InstrumentPnLDTO> convertInstrumentPnLsToInstrumentPnLDTOs(List<InstrumentPnL> instrumentPnLs, InstrumentPnLConvertCriteriaDTO convertCriteria);

	InstrumentPnLDTO getInstrumentPnLDTOById(Integer instrumentPnLId);



	
}
