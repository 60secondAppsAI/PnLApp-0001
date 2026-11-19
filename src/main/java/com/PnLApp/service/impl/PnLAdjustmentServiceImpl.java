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
import com.PnLApp.dao.PnLAdjustmentDAO;
import com.PnLApp.domain.PnLAdjustment;
import com.PnLApp.dto.PnLAdjustmentDTO;
import com.PnLApp.dto.PnLAdjustmentSearchDTO;
import com.PnLApp.dto.PnLAdjustmentPageDTO;
import com.PnLApp.dto.PnLAdjustmentConvertCriteriaDTO;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;
import com.PnLApp.service.PnLAdjustmentService;
import com.PnLApp.util.ControllerUtils;


@Service
public class PnLAdjustmentServiceImpl extends GenericServiceImpl<PnLAdjustment, Integer> implements PnLAdjustmentService {

    private final static Logger logger = LoggerFactory.getLogger(PnLAdjustmentServiceImpl.class);

	@Autowired
	PnLAdjustmentDAO pnLAdjustmentDao;

	

	@Override
	public GenericDAO<PnLAdjustment, Integer> getDAO() {
		return (GenericDAO<PnLAdjustment, Integer>) pnLAdjustmentDao;
	}
	
	public List<PnLAdjustment> findAll () {
		List<PnLAdjustment> pnLAdjustments = pnLAdjustmentDao.findAll();
		
		return pnLAdjustments;	
		
	}

	public ResultDTO addPnLAdjustment(PnLAdjustmentDTO pnLAdjustmentDTO, RequestDTO requestDTO) {

		PnLAdjustment pnLAdjustment = new PnLAdjustment();

		pnLAdjustment.setPnlAdjustmentId(pnLAdjustmentDTO.getPnlAdjustmentId());

		pnLAdjustment.setPnlRecordId(pnLAdjustmentDTO.getPnlRecordId());

		pnLAdjustment.setAdjustmentType(pnLAdjustmentDTO.getAdjustmentType());

		pnLAdjustment.setAmount(pnLAdjustmentDTO.getAmount());

		pnLAdjustment.setDescription(pnLAdjustmentDTO.getDescription());

		pnLAdjustment.setPnLAdjustmentId(pnLAdjustmentDTO.getPnLAdjustmentId());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		pnLAdjustment = pnLAdjustmentDao.save(pnLAdjustment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PnLAdjustment> getAllPnLAdjustments(Pageable pageable) {
		return pnLAdjustmentDao.findAll(pageable);
	}

	public Page<PnLAdjustment> getAllPnLAdjustments(Specification<PnLAdjustment> spec, Pageable pageable) {
		return pnLAdjustmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<PnLAdjustmentPageDTO> getPnLAdjustments(PnLAdjustmentSearchDTO pnLAdjustmentSearchDTO) {
	
			Integer pnlAdjustmentId = pnLAdjustmentSearchDTO.getPnlAdjustmentId(); 
 			Integer pnlRecordId = pnLAdjustmentSearchDTO.getPnlRecordId(); 
 			String adjustmentType = pnLAdjustmentSearchDTO.getAdjustmentType(); 
  			String description = pnLAdjustmentSearchDTO.getDescription(); 
 			Integer pnLAdjustmentId = pnLAdjustmentSearchDTO.getPnLAdjustmentId(); 
 			String sortBy = pnLAdjustmentSearchDTO.getSortBy();
			String sortOrder = pnLAdjustmentSearchDTO.getSortOrder();
			String searchQuery = pnLAdjustmentSearchDTO.getSearchQuery();
			Integer page = pnLAdjustmentSearchDTO.getPage();
			Integer size = pnLAdjustmentSearchDTO.getSize();

	        Specification<PnLAdjustment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, pnlAdjustmentId, "pnlAdjustmentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, pnlRecordId, "pnlRecordId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, adjustmentType, "adjustmentType"); 
			
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			spec = ControllerUtils.andIfNecessary(spec, pnLAdjustmentId, "pnLAdjustmentId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("adjustmentType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<PnLAdjustment> pnLAdjustments = this.getAllPnLAdjustments(spec, pageable);
		
		//System.out.println(String.valueOf(pnLAdjustments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(pnLAdjustments.getTotalPages()));
		
		List<PnLAdjustment> pnLAdjustmentsList = pnLAdjustments.getContent();
		
		PnLAdjustmentConvertCriteriaDTO convertCriteria = new PnLAdjustmentConvertCriteriaDTO();
		List<PnLAdjustmentDTO> pnLAdjustmentDTOs = this.convertPnLAdjustmentsToPnLAdjustmentDTOs(pnLAdjustmentsList,convertCriteria);
		
		PnLAdjustmentPageDTO pnLAdjustmentPageDTO = new PnLAdjustmentPageDTO();
		pnLAdjustmentPageDTO.setPnLAdjustments(pnLAdjustmentDTOs);
		pnLAdjustmentPageDTO.setTotalElements(pnLAdjustments.getTotalElements());
		return ResponseEntity.ok(pnLAdjustmentPageDTO);
	}

	public List<PnLAdjustmentDTO> convertPnLAdjustmentsToPnLAdjustmentDTOs(List<PnLAdjustment> pnLAdjustments, PnLAdjustmentConvertCriteriaDTO convertCriteria) {
		
		List<PnLAdjustmentDTO> pnLAdjustmentDTOs = new ArrayList<PnLAdjustmentDTO>();
		
		for (PnLAdjustment pnLAdjustment : pnLAdjustments) {
			pnLAdjustmentDTOs.add(convertPnLAdjustmentToPnLAdjustmentDTO(pnLAdjustment,convertCriteria));
		}
		
		return pnLAdjustmentDTOs;

	}
	
	public PnLAdjustmentDTO convertPnLAdjustmentToPnLAdjustmentDTO(PnLAdjustment pnLAdjustment, PnLAdjustmentConvertCriteriaDTO convertCriteria) {
		
		PnLAdjustmentDTO pnLAdjustmentDTO = new PnLAdjustmentDTO();

		pnLAdjustmentDTO.setPnlAdjustmentId(pnLAdjustment.getPnlAdjustmentId());

		pnLAdjustmentDTO.setPnlRecordId(pnLAdjustment.getPnlRecordId());

		pnLAdjustmentDTO.setAdjustmentType(pnLAdjustment.getAdjustmentType());

		pnLAdjustmentDTO.setAmount(pnLAdjustment.getAmount());

		pnLAdjustmentDTO.setDescription(pnLAdjustment.getDescription());

		pnLAdjustmentDTO.setPnLAdjustmentId(pnLAdjustment.getPnLAdjustmentId());
		
		return pnLAdjustmentDTO;
	}

	public ResultDTO updatePnLAdjustment(PnLAdjustmentDTO pnLAdjustmentDTO, RequestDTO requestDTO) {
		
		PnLAdjustment pnLAdjustment = pnLAdjustmentDao.getById(pnLAdjustmentDTO.getPnLAdjustmentId());
		
		pnLAdjustment.setPnlAdjustmentId(ControllerUtils.setValue(pnLAdjustment.getPnlAdjustmentId(), pnLAdjustmentDTO.getPnlAdjustmentId()));
		
		pnLAdjustment.setPnlRecordId(ControllerUtils.setValue(pnLAdjustment.getPnlRecordId(), pnLAdjustmentDTO.getPnlRecordId()));
		
		pnLAdjustment.setAdjustmentType(ControllerUtils.setValue(pnLAdjustment.getAdjustmentType(), pnLAdjustmentDTO.getAdjustmentType()));
		
		pnLAdjustment.setAmount(ControllerUtils.setValue(pnLAdjustment.getAmount(), pnLAdjustmentDTO.getAmount()));
		
		pnLAdjustment.setDescription(ControllerUtils.setValue(pnLAdjustment.getDescription(), pnLAdjustmentDTO.getDescription()));
		
		pnLAdjustment.setPnLAdjustmentId(ControllerUtils.setValue(pnLAdjustment.getPnLAdjustmentId(), pnLAdjustmentDTO.getPnLAdjustmentId()));

        pnLAdjustment = pnLAdjustmentDao.save(pnLAdjustment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PnLAdjustmentDTO getPnLAdjustmentDTOById(Integer pnLAdjustmentId) {
	
		PnLAdjustment pnLAdjustment = pnLAdjustmentDao.getById(pnLAdjustmentId);
		
		PnLAdjustmentConvertCriteriaDTO convertCriteria = new PnLAdjustmentConvertCriteriaDTO();
		return(this.convertPnLAdjustmentToPnLAdjustmentDTO(pnLAdjustment,convertCriteria));
	}

}
