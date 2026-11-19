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
@Table(name="strategy_pn_ls")
@Getter @Setter @NoArgsConstructor
public class StrategyPnL {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="strategy_pn_l_id")
	private Integer strategyPnLId;
    
  	@Column(name="pnl_record_id")
	private Integer pnlRecordId;
    
  	@Column(name="strategy_id")
	private Integer strategyId;
    
  	@Column(name="realized_pn_l")
	private Double realizedPnL;
    
  	@Column(name="unrealized_pn_l")
	private Double unrealizedPnL;
    

}
