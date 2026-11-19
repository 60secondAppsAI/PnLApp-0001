package com.PnLApp.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.PnLApp.dao.GenericDAO;
import com.PnLApp.service.GenericService;
import com.PnLApp.service.impl.GenericServiceImpl;
import com.PnLApp.dao.StrategyPnLDAO;
import com.PnLApp.domain.StrategyPnL;
import com.PnLApp.dto.StrategyPnLDTO;
import com.PnLApp.dto.StrategyPnLSearchDTO;
import com.PnLApp.dto.StrategyPnLPageDTO;
import com.PnLApp.dto.StrategyPnLConvertCriteriaDTO;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;
import com.PnLApp.service.StrategyPnLService;
import com.PnLApp.util.ControllerUtils;


@Service
public class StrategyPnLServiceImpl extends GenericServiceImpl<StrategyPnL, Integer> implements StrategyPnLService {

    private final static Logger logger = LoggerFactory.getLogger(StrategyPnLServiceImpl.class);

	@Autowired
	StrategyPnLDAO strategyPnLDao;

	

	@Override
	public GenericDAO<StrategyPnL, Integer> getDAO() {
		return (GenericDAO<StrategyPnL, Integer>) strategyPnLDao;
	}
	
	public List<StrategyPnL> findAll () {
		List<StrategyPnL> strategyPnLs = strategyPnLDao.findAll();
		
		return strategyPnLs;	
		
	}

	public ResultDTO addStrategyPnL(StrategyPnLDTO strategyPnLDTO, RequestDTO requestDTO) {

		StrategyPnL strategyPnL = new StrategyPnL();

		strategyPnL.setStrategyPnLId(strategyPnLDTO.getStrategyPnLId());

		strategyPnL.setPnlRecordId(strategyPnLDTO.getPnlRecordId());

		strategyPnL.setStrategyId(strategyPnLDTO.getStrategyId());

		strategyPnL.setRealizedPnL(strategyPnLDTO.getRealizedPnL());

		strategyPnL.setUnrealizedPnL(strategyPnLDTO.getUnrealizedPnL());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		strategyPnL = strategyPnLDao.save(strategyPnL);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<StrategyPnL> getAllStrategyPnLs(Pageable pageable) {
		return strategyPnLDao.findAll(pageable);
	}

	public Page<StrategyPnL> getAllStrategyPnLs(Specification<StrategyPnL> spec, Pageable pageable) {
		return strategyPnLDao.findAll(spec, pageable);
	}

	public ResponseEntity<StrategyPnLPageDTO> getStrategyPnLs(StrategyPnLSearchDTO strategyPnLSearchDTO) {
	
			Integer strategyPnLId = strategyPnLSearchDTO.getStrategyPnLId(); 
 			Integer pnlRecordId = strategyPnLSearchDTO.getPnlRecordId(); 
 			Integer strategyId = strategyPnLSearchDTO.getStrategyId(); 
   			String sortBy = strategyPnLSearchDTO.getSortBy();
			String sortOrder = strategyPnLSearchDTO.getSortOrder();
			String searchQuery = strategyPnLSearchDTO.getSearchQuery();
			Integer page = strategyPnLSearchDTO.getPage();
			Integer size = strategyPnLSearchDTO.getSize();

	        Specification<StrategyPnL> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, strategyPnLId, "strategyPnLId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, pnlRecordId, "pnlRecordId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, strategyId, "strategyId"); 
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<StrategyPnL> strategyPnLs = this.getAllStrategyPnLs(spec, pageable);
		
		//System.out.println(String.valueOf(strategyPnLs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(strategyPnLs.getTotalPages()));
		
		List<StrategyPnL> strategyPnLsList = strategyPnLs.getContent();
		
		StrategyPnLConvertCriteriaDTO convertCriteria = new StrategyPnLConvertCriteriaDTO();
		List<StrategyPnLDTO> strategyPnLDTOs = this.convertStrategyPnLsToStrategyPnLDTOs(strategyPnLsList,convertCriteria);
		
		StrategyPnLPageDTO strategyPnLPageDTO = new StrategyPnLPageDTO();
		strategyPnLPageDTO.setStrategyPnLs(strategyPnLDTOs);
		strategyPnLPageDTO.setTotalElements(strategyPnLs.getTotalElements());
		return ResponseEntity.ok(strategyPnLPageDTO);
	}

	public List<StrategyPnLDTO> convertStrategyPnLsToStrategyPnLDTOs(List<StrategyPnL> strategyPnLs, StrategyPnLConvertCriteriaDTO convertCriteria) {
		
		List<StrategyPnLDTO> strategyPnLDTOs = new ArrayList<StrategyPnLDTO>();
		
		for (StrategyPnL strategyPnL : strategyPnLs) {
			strategyPnLDTOs.add(convertStrategyPnLToStrategyPnLDTO(strategyPnL,convertCriteria));
		}
		
		return strategyPnLDTOs;

	}
	
	public StrategyPnLDTO convertStrategyPnLToStrategyPnLDTO(StrategyPnL strategyPnL, StrategyPnLConvertCriteriaDTO convertCriteria) {
		
		StrategyPnLDTO strategyPnLDTO = new StrategyPnLDTO();

		strategyPnLDTO.setStrategyPnLId(strategyPnL.getStrategyPnLId());

		strategyPnLDTO.setPnlRecordId(strategyPnL.getPnlRecordId());

		strategyPnLDTO.setStrategyId(strategyPnL.getStrategyId());

		strategyPnLDTO.setRealizedPnL(strategyPnL.getRealizedPnL());

		strategyPnLDTO.setUnrealizedPnL(strategyPnL.getUnrealizedPnL());
		
		return strategyPnLDTO;
	}

	public ResultDTO updateStrategyPnL(StrategyPnLDTO strategyPnLDTO, RequestDTO requestDTO) {
		
		StrategyPnL strategyPnL = strategyPnLDao.getById(strategyPnLDTO.getStrategyPnLId());
		
		strategyPnL.setStrategyPnLId(ControllerUtils.setValue(strategyPnL.getStrategyPnLId(), strategyPnLDTO.getStrategyPnLId()));
		
		strategyPnL.setPnlRecordId(ControllerUtils.setValue(strategyPnL.getPnlRecordId(), strategyPnLDTO.getPnlRecordId()));
		
		strategyPnL.setStrategyId(ControllerUtils.setValue(strategyPnL.getStrategyId(), strategyPnLDTO.getStrategyId()));
		
		strategyPnL.setRealizedPnL(ControllerUtils.setValue(strategyPnL.getRealizedPnL(), strategyPnLDTO.getRealizedPnL()));
		
		strategyPnL.setUnrealizedPnL(ControllerUtils.setValue(strategyPnL.getUnrealizedPnL(), strategyPnLDTO.getUnrealizedPnL()));

        strategyPnL = strategyPnLDao.save(strategyPnL);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public StrategyPnLDTO getStrategyPnLDTOById(Integer strategyPnLId) {
	
		StrategyPnL strategyPnL = strategyPnLDao.getById(strategyPnLId);
		
		StrategyPnLConvertCriteriaDTO convertCriteria = new StrategyPnLConvertCriteriaDTO();
		return(this.convertStrategyPnLToStrategyPnLDTO(strategyPnL,convertCriteria));
	}

}
