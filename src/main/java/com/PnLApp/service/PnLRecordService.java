package com.PnLApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.PnLApp.domain.PnLRecord;
import com.PnLApp.dto.PnLRecordDTO;
import com.PnLApp.dto.PnLRecordSearchDTO;
import com.PnLApp.dto.PnLRecordPageDTO;
import com.PnLApp.dto.PnLRecordConvertCriteriaDTO;
import com.PnLApp.service.GenericService;
import com.PnLApp.dto.common.RequestDTO;
import com.PnLApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PnLRecordService extends GenericService<PnLRecord, Integer> {

	List<PnLRecord> findAll();

	ResultDTO addPnLRecord(PnLRecordDTO pnLRecordDTO, RequestDTO requestDTO);

	ResultDTO updatePnLRecord(PnLRecordDTO pnLRecordDTO, RequestDTO requestDTO);

    Page<PnLRecord> getAllPnLRecords(Pageable pageable);

    Page<PnLRecord> getAllPnLRecords(Specification<PnLRecord> spec, Pageable pageable);

	ResponseEntity<PnLRecordPageDTO> getPnLRecords(PnLRecordSearchDTO pnLRecordSearchDTO);
	
	List<PnLRecordDTO> convertPnLRecordsToPnLRecordDTOs(List<PnLRecord> pnLRecords, PnLRecordConvertCriteriaDTO convertCriteria);

	PnLRecordDTO getPnLRecordDTOById(Integer pnLRecordId);



	
}
