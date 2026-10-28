package com.PnLApp.dao;

import java.util.List;
import java.util.Date;

import com.PnLApp.dao.GenericDAO;
import com.PnLApp.domain.PnLRecord;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface PnLRecordDAO extends GenericDAO<PnLRecord, Integer> {
  
	List<PnLRecord> findAll();
	


}

