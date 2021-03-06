package com.example.Taller1QuinteroLuisa.backend.model.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.Taller1QuinteroLuisa.backend.validation.SalesPersonQuotaHistoryValidation;

/**
 * The persistent class for the salespersonquotahistory database table.
 *
 */
@Entity
@NamedQuery(name = "Salespersonquotahistory.findAll", query = "SELECT s FROM Salespersonquotahistory s")
public class Salespersonquotahistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SALESPERSONQUOTAHISTORY_BUSINESSENTITYID_GENERATOR", allocationSize = 1, sequenceName = "SALESPERSONQUOTAHISTORY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALESPERSONQUOTAHISTORY_BUSINESSENTITYID_GENERATOR")
	private Integer businessentityid;
	
	/**@EmbeddedId
	private SalespersonquotahistoryPK id;
	*/
	
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	@PastOrPresent(groups= SalesPersonQuotaHistoryValidation.class, message= "Date cannot be greater than the currrent date")
	private LocalDate modifieddate;

	private Integer rowguid;

	@PositiveOrZero(groups= SalesPersonQuotaHistoryValidation.class, message="It has to be greater than zero")
	private BigDecimal salesquota;

	// bi-directional many-to-one association to Salesperson
	@ManyToOne
	@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	@NotNull
	private Salesperson salesperson;

	public Salespersonquotahistory() {
	}

//	public SalespersonquotahistoryPK getId() {
//		return this.id;
//	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Salesperson getSalesperson() {
		return this.salesperson;
	}
	

	public BigDecimal getSalesquota() {
		return this.salesquota;
	}

//	public void setId(SalespersonquotahistoryPK id) {
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

	public void setSalesquota(BigDecimal salesquota) {
		this.salesquota = salesquota;
	}

	public Integer getBusinessentityid() {
		return businessentityid;
	}

	public void setBusinessentityid(Integer businessentityid) {
		this.businessentityid = businessentityid;
	}

	

}