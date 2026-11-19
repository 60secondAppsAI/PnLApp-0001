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
import com.PnLApp.dao.PnLRecordDAO;
import com.PnLApp.domain.PnLRecord;
import com.PnLApp.dto.PnLRecordDTO;
import com.PnLApp.dto.PnLRecordSearchDTO;
import com.PnLApp.dto.PnLRecordPageDTO;
import com.PnLApp.dto.PnLRecordConvertCriteriaDTO;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;
import com.PnLApp.service.PnLRecordService;
import com.PnLApp.util.ControllerUtils;


@Service
public class PnLRecordServiceImpl extends GenericServiceImpl<PnLRecord, Integer> implements PnLRecordService {

    private final static Logger logger = LoggerFactory.getLogger(PnLRecordServiceImpl.class);

	@Autowired
	PnLRecordDAO pnLRecordDao;

	

	@Override
	public GenericDAO<PnLRecord, Integer> getDAO() {
		return (GenericDAO<PnLRecord, Integer>) pnLRecordDao;
	}
	
	public List<PnLRecord> findAll () {
		List<PnLRecord> pnLRecords = pnLRecordDao.findAll();
		
		return pnLRecords;	
		
	}

	public ResultDTO addPnLRecord(PnLRecordDTO pnLRecordDTO, RequestDTO requestDTO) {

		PnLRecord pnLRecord = new PnLRecord();

		pnLRecord.setPnlRecordId(pnLRecordDTO.getPnlRecordId());

		pnLRecord.setPortfolioId(pnLRecordDTO.getPortfolioId());

		pnLRecord.setCalculationDate(pnLRecordDTO.getCalculationDate());

		pnLRecord.setRealizedPnL(pnLRecordDTO.getRealizedPnL());

		pnLRecord.setUnrealizedPnL(pnLRecordDTO.getUnrealizedPnL());

		pnLRecord.setTotalPnL(pnLRecordDTO.getTotalPnL());

		pnLRecord.setPnLRecordId(pnLRecordDTO.getPnLRecordId());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		pnLRecord = pnLRecordDao.save(pnLRecord);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PnLRecord> getAllPnLRecords(Pageable pageable) {
		return pnLRecordDao.findAll(pageable);
	}

	public Page<PnLRecord> getAllPnLRecords(Specification<PnLRecord> spec, Pageable pageable) {
		return pnLRecordDao.findAll(spec, pageable);
	}

	public ResponseEntity<PnLRecordPageDTO> getPnLRecords(PnLRecordSearchDTO pnLRecordSearchDTO) {
	
			Integer pnlRecordId = pnLRecordSearchDTO.getPnlRecordId(); 
 			Integer portfolioId = pnLRecordSearchDTO.getPortfolioId(); 
 			Date calculationDate = pnLRecordSearchDTO.getCalculationDate();
    			Integer pnLRecordId = pnLRecordSearchDTO.getPnLRecordId(); 
 			String sortBy = pnLRecordSearchDTO.getSortBy();
			String sortOrder = pnLRecordSearchDTO.getSortOrder();
			String searchQuery = pnLRecordSearchDTO.getSearchQuery();
			Integer page = pnLRecordSearchDTO.getPage();
			Integer size = pnLRecordSearchDTO.getSize();

	        Specification<PnLRecord> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, pnlRecordId, "pnlRecordId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, portfolioId, "portfolioId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, calculationDate, "calculationDate");
			
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, pnLRecordId, "pnLRecordId"); 
			

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

		Page<PnLRecord> pnLRecords = this.getAllPnLRecords(spec, pageable);
		
		//System.out.println(String.valueOf(pnLRecords.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(pnLRecords.getTotalPages()));
		
		List<PnLRecord> pnLRecordsList = pnLRecords.getContent();
		
		PnLRecordConvertCriteriaDTO convertCriteria = new PnLRecordConvertCriteriaDTO();
		List<PnLRecordDTO> pnLRecordDTOs = this.convertPnLRecordsToPnLRecordDTOs(pnLRecordsList,convertCriteria);
		
		PnLRecordPageDTO pnLRecordPageDTO = new PnLRecordPageDTO();
		pnLRecordPageDTO.setPnLRecords(pnLRecordDTOs);
		pnLRecordPageDTO.setTotalElements(pnLRecords.getTotalElements());
		return ResponseEntity.ok(pnLRecordPageDTO);
	}

	public List<PnLRecordDTO> convertPnLRecordsToPnLRecordDTOs(List<PnLRecord> pnLRecords, PnLRecordConvertCriteriaDTO convertCriteria) {
		
		List<PnLRecordDTO> pnLRecordDTOs = new ArrayList<PnLRecordDTO>();
		
		for (PnLRecord pnLRecord : pnLRecords) {
			pnLRecordDTOs.add(convertPnLRecordToPnLRecordDTO(pnLRecord,convertCriteria));
		}
		
		return pnLRecordDTOs;

	}
	
	public PnLRecordDTO convertPnLRecordToPnLRecordDTO(PnLRecord pnLRecord, PnLRecordConvertCriteriaDTO convertCriteria) {
		
		PnLRecordDTO pnLRecordDTO = new PnLRecordDTO();

		pnLRecordDTO.setPnlRecordId(pnLRecord.getPnlRecordId());

		pnLRecordDTO.setPortfolioId(pnLRecord.getPortfolioId());

		pnLRecordDTO.setCalculationDate(pnLRecord.getCalculationDate());

		pnLRecordDTO.setRealizedPnL(pnLRecord.getRealizedPnL());

		pnLRecordDTO.setUnrealizedPnL(pnLRecord.getUnrealizedPnL());

		pnLRecordDTO.setTotalPnL(pnLRecord.getTotalPnL());

		pnLRecordDTO.setPnLRecordId(pnLRecord.getPnLRecordId());
		
		return pnLRecordDTO;
	}

	public ResultDTO updatePnLRecord(PnLRecordDTO pnLRecordDTO, RequestDTO requestDTO) {
		
		PnLRecord pnLRecord = pnLRecordDao.getById(pnLRecordDTO.getPnLRecordId());
		
		pnLRecord.setPnlRecordId(ControllerUtils.setValue(pnLRecord.getPnlRecordId(), pnLRecordDTO.getPnlRecordId()));
		
		pnLRecord.setPortfolioId(ControllerUtils.setValue(pnLRecord.getPortfolioId(), pnLRecordDTO.getPortfolioId()));
		
		pnLRecord.setCalculationDate(ControllerUtils.setValue(pnLRecord.getCalculationDate(), pnLRecordDTO.getCalculationDate()));
		
		pnLRecord.setRealizedPnL(ControllerUtils.setValue(pnLRecord.getRealizedPnL(), pnLRecordDTO.getRealizedPnL()));
		
		pnLRecord.setUnrealizedPnL(ControllerUtils.setValue(pnLRecord.getUnrealizedPnL(), pnLRecordDTO.getUnrealizedPnL()));
		
		pnLRecord.setTotalPnL(ControllerUtils.setValue(pnLRecord.getTotalPnL(), pnLRecordDTO.getTotalPnL()));
		
		pnLRecord.setPnLRecordId(ControllerUtils.setValue(pnLRecord.getPnLRecordId(), pnLRecordDTO.getPnLRecordId()));

        pnLRecord = pnLRecordDao.save(pnLRecord);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PnLRecordDTO getPnLRecordDTOById(Integer pnLRecordId) {
	
		PnLRecord pnLRecord = pnLRecordDao.getById(pnLRecordId);
		
		PnLRecordConvertCriteriaDTO convertCriteria = new PnLRecordConvertCriteriaDTO();
		return(this.convertPnLRecordToPnLRecordDTO(pnLRecord,convertCriteria));
	}

}
