package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;

public interface SalesPersonQuotaHistoryService {
	
	public Salespersonquotahistory save(Salespersonquotahistory s) throws Exception;
	public Salespersonquotahistory update(Salespersonquotahistory s) throws Exception;

}
