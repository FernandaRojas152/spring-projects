package com.example.Taller1QuinteroLuisa.backend.model.sales;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.Taller1QuinteroLuisa.backend.validation.SalesTerritoryHistoryValidation;

/**
 * The persistent class for the salesterritoryhistory database table.
 *
 */
@Entity
@NamedQuery(name = "Salesterritoryhistory.findAll", query = "SELECT s FROM Salesterritoryhistory s")
public class Salesterritoryhistory implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private SalesterritoryhistoryPK id;
	
	@Id
	@SequenceGenerator(name = "SALESTERRITORYHISTORY_ID_GENERATOR", allocationSize = 1, sequenceName = "SALESTERRITORYHISTORY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALESTERRITORYHISTORY_ID_GENERATOR")
	private Integer id;
	
	@Future(groups= SalesTerritoryHistoryValidation.class)
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private LocalDate enddate;
	
	@PastOrPresent(groups= SalesTerritoryHistoryValidation.class, message= "Date cannot be greater than the currrent date")
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private LocalDate startdate;
	
	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private LocalDate modifieddate;

	private Integer rowguid;

	// bi-directional many-to-one association to Salesperson
	@ManyToOne
	@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Salesperson salesperson;

	// bi-directional many-to-one association to Salesterritory
	@ManyToOne
	@JoinColumn(name = "territoryid")
	@NotNull(groups= SalesTerritoryHistoryValidation.class)
	private Salesterritory salesterritory;

	public Salesterritoryhistory() {
	}

	public LocalDate getEnddate() {
		return this.enddate;
	}

//	public SalesterritoryhistoryPK getId() {
//		return this.id;
//	}
	
	public Integer getId() {
		return this.id;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Salesperson getSalesperson() {
		return this.salesperson;
	}

	public Salesterritory getSalesterritory() {
		return this.salesterritory;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

//	public void setId(SalesterritoryhistoryPK id) {
//		this.id = id;
//	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSalesperson(Salesperson salesperson) {
		this.salesperson = salesperson;
	}

	public void setSalesterritory(Salesterritory salesterritory) {
		this.salesterritory = salesterritory;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

}