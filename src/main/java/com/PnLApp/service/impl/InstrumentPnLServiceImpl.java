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
import com.PnLApp.dao.InstrumentPnLDAO;
import com.PnLApp.domain.InstrumentPnL;
import com.PnLApp.dto.InstrumentPnLDTO;
import com.PnLApp.dto.InstrumentPnLSearchDTO;
import com.PnLApp.dto.InstrumentPnLPageDTO;
import com.PnLApp.dto.InstrumentPnLConvertCriteriaDTO;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;
import com.PnLApp.service.InstrumentPnLService;
import com.PnLApp.util.ControllerUtils;


@Service
public class InstrumentPnLServiceImpl extends GenericServiceImpl<InstrumentPnL, Integer> implements InstrumentPnLService {

    private final static Logger logger = LoggerFactory.getLogger(InstrumentPnLServiceImpl.class);

	@Autowired
	InstrumentPnLDAO instrumentPnLDao;

	

	@Override
	public GenericDAO<InstrumentPnL, Integer> getDAO() {
		return (GenericDAO<InstrumentPnL, Integer>) instrumentPnLDao;
	}
	
	public List<InstrumentPnL> findAll () {
		List<InstrumentPnL> instrumentPnLs = instrumentPnLDao.findAll();
		
		return instrumentPnLs;	
		
	}

	public ResultDTO addInstrumentPnL(InstrumentPnLDTO instrumentPnLDTO, RequestDTO requestDTO) {

		InstrumentPnL instrumentPnL = new InstrumentPnL();

		instrumentPnL.setInstrumentPnLId(instrumentPnLDTO.getInstrumentPnLId());

		instrumentPnL.setPnlRecordId(instrumentPnLDTO.getPnlRecordId());

		instrumentPnL.setInstrumentId(instrumentPnLDTO.getInstrumentId());

		instrumentPnL.setRealizedPnL(instrumentPnLDTO.getRealizedPnL());

		instrumentPnL.setUnrealizedPnL(instrumentPnLDTO.getUnrealizedPnL());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		instrumentPnL = instrumentPnLDao.save(instrumentPnL);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<InstrumentPnL> getAllInstrumentPnLs(Pageable pageable) {
		return instrumentPnLDao.findAll(pageable);
	}

	public Page<InstrumentPnL> getAllInstrumentPnLs(Specification<InstrumentPnL> spec, Pageable pageable) {
		return instrumentPnLDao.findAll(spec, pageable);
	}

	public ResponseEntity<InstrumentPnLPageDTO> getInstrumentPnLs(InstrumentPnLSearchDTO instrumentPnLSearchDTO) {
	
			Integer instrumentPnLId = instrumentPnLSearchDTO.getInstrumentPnLId(); 
 			Integer pnlRecordId = instrumentPnLSearchDTO.getPnlRecordId(); 
 			Integer instrumentId = instrumentPnLSearchDTO.getInstrumentId(); 
   			String sortBy = instrumentPnLSearchDTO.getSortBy();
			String sortOrder = instrumentPnLSearchDTO.getSortOrder();
			String searchQuery = instrumentPnLSearchDTO.getSearchQuery();
			Integer page = instrumentPnLSearchDTO.getPage();
			Integer size = instrumentPnLSearchDTO.getSize();

	        Specification<InstrumentPnL> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, instrumentPnLId, "instrumentPnLId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, pnlRecordId, "pnlRecordId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, instrumentId, "instrumentId"); 
			
			
			

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

		Page<InstrumentPnL> instrumentPnLs = this.getAllInstrumentPnLs(spec, pageable);
		
		//System.out.println(String.valueOf(instrumentPnLs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(instrumentPnLs.getTotalPages()));
		
		List<InstrumentPnL> instrumentPnLsList = instrumentPnLs.getContent();
		
		InstrumentPnLConvertCriteriaDTO convertCriteria = new InstrumentPnLConvertCriteriaDTO();
		List<InstrumentPnLDTO> instrumentPnLDTOs = this.convertInstrumentPnLsToInstrumentPnLDTOs(instrumentPnLsList,convertCriteria);
		
		InstrumentPnLPageDTO instrumentPnLPageDTO = new InstrumentPnLPageDTO();
		instrumentPnLPageDTO.setInstrumentPnLs(instrumentPnLDTOs);
		instrumentPnLPageDTO.setTotalElements(instrumentPnLs.getTotalElements());
		return ResponseEntity.ok(instrumentPnLPageDTO);
	}

	public List<InstrumentPnLDTO> convertInstrumentPnLsToInstrumentPnLDTOs(List<InstrumentPnL> instrumentPnLs, InstrumentPnLConvertCriteriaDTO convertCriteria) {
		
		List<InstrumentPnLDTO> instrumentPnLDTOs = new ArrayList<InstrumentPnLDTO>();
		
		for (InstrumentPnL instrumentPnL : instrumentPnLs) {
			instrumentPnLDTOs.add(convertInstrumentPnLToInstrumentPnLDTO(instrumentPnL,convertCriteria));
		}
		
		return instrumentPnLDTOs;

	}
	
	public InstrumentPnLDTO convertInstrumentPnLToInstrumentPnLDTO(InstrumentPnL instrumentPnL, InstrumentPnLConvertCriteriaDTO convertCriteria) {
		
		InstrumentPnLDTO instrumentPnLDTO = new InstrumentPnLDTO();

		instrumentPnLDTO.setInstrumentPnLId(instrumentPnL.getInstrumentPnLId());

		instrumentPnLDTO.setPnlRecordId(instrumentPnL.getPnlRecordId());

		instrumentPnLDTO.setInstrumentId(instrumentPnL.getInstrumentId());

		instrumentPnLDTO.setRealizedPnL(instrumentPnL.getRealizedPnL());

		instrumentPnLDTO.setUnrealizedPnL(instrumentPnL.getUnrealizedPnL());
		
		return instrumentPnLDTO;
	}

	public ResultDTO updateInstrumentPnL(InstrumentPnLDTO instrumentPnLDTO, RequestDTO requestDTO) {
		
		InstrumentPnL instrumentPnL = instrumentPnLDao.getById(instrumentPnLDTO.getInstrumentPnLId());
		
		instrumentPnL.setInstrumentPnLId(ControllerUtils.setValue(instrumentPnL.getInstrumentPnLId(), instrumentPnLDTO.getInstrumentPnLId()));
		
		instrumentPnL.setPnlRecordId(ControllerUtils.setValue(instrumentPnL.getPnlRecordId(), instrumentPnLDTO.getPnlRecordId()));
		
		instrumentPnL.setInstrumentId(ControllerUtils.setValue(instrumentPnL.getInstrumentId(), instrumentPnLDTO.getInstrumentId()));
		
		instrumentPnL.setRealizedPnL(ControllerUtils.setValue(instrumentPnL.getRealizedPnL(), instrumentPnLDTO.getRealizedPnL()));
		
		instrumentPnL.setUnrealizedPnL(ControllerUtils.setValue(instrumentPnL.getUnrealizedPnL(), instrumentPnLDTO.getUnrealizedPnL()));

        instrumentPnL = instrumentPnLDao.save(instrumentPnL);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public InstrumentPnLDTO getInstrumentPnLDTOById(Integer instrumentPnLId) {
	
		InstrumentPnL instrumentPnL = instrumentPnLDao.getById(instrumentPnLId);
		
		InstrumentPnLConvertCriteriaDTO convertCriteria = new InstrumentPnLConvertCriteriaDTO();
		return(this.convertInstrumentPnLToInstrumentPnLDTO(instrumentPnL,convertCriteria));
	}

}
