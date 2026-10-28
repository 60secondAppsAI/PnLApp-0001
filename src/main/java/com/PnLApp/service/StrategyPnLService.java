package com.PnLApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.PnLApp.domain.StrategyPnL;
import com.PnLApp.dto.StrategyPnLDTO;
import com.PnLApp.dto.StrategyPnLSearchDTO;
import com.PnLApp.dto.StrategyPnLPageDTO;
import com.PnLApp.dto.StrategyPnLConvertCriteriaDTO;
import com.PnLApp.service.GenericService;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface StrategyPnLService extends GenericService<StrategyPnL, Integer> {

	List<StrategyPnL> findAll();

	ResultDTO addStrategyPnL(StrategyPnLDTO strategyPnLDTO, RequestDTO requestDTO);

	ResultDTO updateStrategyPnL(StrategyPnLDTO strategyPnLDTO, RequestDTO requestDTO);

    Page<StrategyPnL> getAllStrategyPnLs(Pageable pageable);

    Page<StrategyPnL> getAllStrategyPnLs(Specification<StrategyPnL> spec, Pageable pageable);

	ResponseEntity<StrategyPnLPageDTO> getStrategyPnLs(StrategyPnLSearchDTO strategyPnLSearchDTO);
	
	List<StrategyPnLDTO> convertStrategyPnLsToStrategyPnLDTOs(List<StrategyPnL> strategyPnLs, StrategyPnLConvertCriteriaDTO convertCriteria);

	StrategyPnLDTO getStrategyPnLDTOById(Integer strategyPnLId);



	
}
