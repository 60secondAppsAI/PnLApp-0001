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
@Table(name="pn_l_records")
@Getter @Setter @NoArgsConstructor
public class PnLRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="pnl_record_id")
	private Integer pnlRecordId;
    
  	@Column(name="portfolio_id")
	private Integer portfolioId;
    
  	@Column(name="calculation_date")
	private Date calculationDate;
    
  	@Column(name="realized_pn_l")
	private Double realizedPnL;
    
  	@Column(name="unrealized_pn_l")
	private Double unrealizedPnL;
    
  	@Column(name="total_pn_l")
	private Double totalPnL;
    
  	@Column(name="pn_l_record_id")
	private Integer pnLRecordId;
    

}
