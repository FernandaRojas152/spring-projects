package com.example.Taller1QuinteroLuisa.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Taller1QuinteroLuisa.controller.interfaces.OperatorController;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.services.SalesPersonQuotaHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.services.SalesPersonServiceImp;
import com.example.Taller1QuinteroLuisa.services.SalesTerritoryHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.services.SalesTerritoryServiceImp;
import com.example.Taller1QuinteroLuisa.validation.SalesPersonQuotaHistoryValidation;

@Controller
public class OperatorControllerImp implements OperatorController{
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	private SalesPersonServiceImp personService;
	private SalesTerritoryHistoryServiceImp territoryHistoryService;
	private SalesTerritoryServiceImp territoryService;
	
	@Autowired
	public OperatorControllerImp(SalesPersonQuotaHistoryServiceImp personQuotaService, SalesTerritoryHistoryServiceImp territoryHistoryService,
			SalesPersonServiceImp personService, SalesTerritoryServiceImp territoryService) {
		this.personQuotaService= personQuotaService;
		this.personService= personService;
		this.territoryService= territoryService;
		this.territoryHistoryService= territoryHistoryService;
	}
	
	@GetMapping("/operator")
	public String operator(Model model) {
		return "operator/operator";
	}
	
	
	/** Salesperson quota history mapping */
	@GetMapping("/salespersonquotahistory")
	public String salesPersonQuota(Model model) {
		model.addAttribute("salespersoquotahistory", personQuotaService.findAll());
		return "operator/salespersonquotahistory";
	}
	
	@GetMapping("/salespersonquotahistory/add")
	public String addSalesPersonQuota(Model model){
		model.addAttribute("salespersonquotahistory", new Salespersonquotahistory());
		model.addAttribute("salespersons", personService.findAll());
		return "operator/add-salespersonquotahistory";
	}
	
	@PostMapping("/salespersonquotahistory/add")
	public String saveSalesPersonQuota(@Validated(SalesPersonQuotaHistoryValidation.class)
		@ModelAttribute Salespersonquotahistory salespersonquotahistory, BindingResult bindingResult, Model model,
		@RequestParam(value="action", required= true)String action) throws Exception {
		if(action.equals("Cancel")) {
			return "redirect:/salespersonquotahistory/";
		}
		if(bindingResult.hasErrors()){
			model.addAttribute("salespersonquotahistory", salespersonquotahistory);
			model.addAttribute("salesperson");
			return "/operator/add-salespersonquotahistory";
		}else {
			personQuotaService.save(salespersonquotahistory);
			System.out.println("aqui ta: "+ personQuotaService.findAll().toString() + salespersonquotahistory.getSalesquota());
			System.out.println("persona:"+ salespersonquotahistory.getSalesperson().getBusinessentityid()
					+ "id: " + salespersonquotahistory.getBusinessentityid());
			return "redirect:/salespersonquotahistory/";
		}
	}
	
	
	/** Salesterritory history mapping */
	@GetMapping("/salesterritoryhistory")
	public String salesTerritoryHistory() {
		return "operator/salesterritoryhistory";
	}

}
