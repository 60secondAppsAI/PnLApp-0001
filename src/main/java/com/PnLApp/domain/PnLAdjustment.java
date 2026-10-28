package com.PnLApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;

@Entity
@Table(name="pn_l_adjustments")
@Getter @Setter @NoArgsConstructor
public class PnLAdjustment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="pnl_adjustment_id")
	private Integer pnlAdjustmentId;
    
  	@Column(name="pnl_record_id")
	private Integer pnlRecordId;
    
  	@Column(name="adjustment_type")
	private String adjustmentType;
    
  	@Column(name="amount")
	private Double amount;
    
  	@Column(name="description")
	private String description;
    
  	@Column(name="pn_l_adjustment_id")
	private Integer pnLAdjustmentId;
    

}
