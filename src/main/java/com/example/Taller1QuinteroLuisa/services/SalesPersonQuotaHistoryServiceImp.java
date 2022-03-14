package com.example.Taller1QuinteroLuisa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonQuotaHistoryRepository;

@Service
public class SalesPersonQuotaHistoryServiceImp implements SalesPersonQuotaHistoryService{
	private SalesPersonQuotaHistoryRepository spq;
	
	@Autowired
	public SalesPersonQuotaHistoryServiceImp() {
		// TODO Auto-generated constructor stub
	}

}
