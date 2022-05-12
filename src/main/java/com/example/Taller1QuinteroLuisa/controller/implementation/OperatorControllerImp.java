package com.example.Taller1QuinteroLuisa.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.Taller1QuinteroLuisa.controller.interfaces.OperatorController;
import com.example.Taller1QuinteroLuisa.services.SalesPersonQuotaHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.services.SalesTerritoryHistoryServiceImp;

@Controller
public class OperatorControllerImp implements OperatorController{
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	private SalesTerritoryHistoryServiceImp territoryHistoryService;
	
	@Autowired
	public OperatorControllerImp(SalesPersonQuotaHistoryServiceImp personQuotaService, SalesTerritoryHistoryServiceImp territoryHistoryService) {
		this.personQuotaService= personQuotaService;
		this.territoryHistoryService= territoryHistoryService;
	}
	
	@GetMapping("/operator")
	public String operator(Model model) {
		return "operator/operator";
	}
	
	
	/** Salesperson quota history mapping */
	@GetMapping("/salespersonquotahistory")
	public String salesPersonQuota(Model model) {
		model.addAttribute("salespersoquotahistory", personQuotaService);
		return "operator/salespersonquotahistory";
	}
	
	
	/** Salesterritory history mapping */
	@GetMapping("/salesterritoryhistory")
	public String salesTerritoryHistory() {
		return "operator/salesterritoryhistory";
	}

}
