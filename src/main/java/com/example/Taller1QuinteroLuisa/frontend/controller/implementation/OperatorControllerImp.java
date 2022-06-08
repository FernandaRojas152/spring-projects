package com.example.Taller1QuinteroLuisa.frontend.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonQuotaHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.validation.CredentialInfoValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.SalesPersonQuotaHistoryValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.SalesTerritoryHistoryValidation;
import com.example.Taller1QuinteroLuisa.frontend.businessdelegate.BusinessDelegate;
import com.example.Taller1QuinteroLuisa.frontend.controller.interfaces.OperatorController;

@Controller
public class OperatorControllerImp implements OperatorController{
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	private SalesPersonServiceImp personService;
	private SalesTerritoryHistoryServiceImp territoryHistoryService;
	private SalesTerritoryServiceImp territoryService;
	
	@Autowired
	private BusinessDelegate businessDelegate;
	
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
		model.addAttribute("salespersonquotahistory", businessDelegate.getSalespersonQuota());
		return "operator/salespersonquotahistory";
	}
	
	@GetMapping("/salespersonquotahistory/add")
	public String addSalesPersonQuota(Model model){
		model.addAttribute("salespersonquotahistory", new Salespersonquotahistory());
		model.addAttribute("salespersons", businessDelegate.getSalesPerson());
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
			model.addAttribute("salesperson", businessDelegate.getSalesPerson());
			return "/operator/add-salespersonquotahistory";
		}else {
			this.businessDelegate.addPersonQuota(salespersonquotahistory);
//			personQuotaService.save(salespersonquotahistory);
			return "redirect:/salespersonquotahistory/";
		}
	}
	
	@GetMapping("/salespersonquotahistory/update/{id}")
	public String editSalesPersonQuota(@PathVariable("id")Integer id, Model model) {
//		Optional<Salespersonquotahistory> s = personQuotaService.findById(id);
		Salespersonquotahistory s= businessDelegate.findByIdPersonQuota(id);
		if(s.equals(null)) {
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("salespersonquotahistory", s);
		model.addAttribute("salespersons", businessDelegate.getSalesPerson());
		return "operator/update-salespersonquotahistory";
	}
	
	@PostMapping("/salespersonquotahistory/update/{id}")
	public String updateSalesPersonQuota(@PathVariable("id")Integer id,
			@Validated(CredentialInfoValidation.class) Salespersonquotahistory salespersonquotahistory, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) throws Exception{
		if(!action.equals("Cancel")){
			if(bindingResult.hasErrors()){
				model.addAttribute("salespersonquotahistory", salespersonquotahistory);
				model.addAttribute("salespersons", businessDelegate.getSalesPerson());
				return "operator/update-salespersonquotahistory";
			}
			salespersonquotahistory.setBusinessentityid(id);
			businessDelegate.updatePersonQuota(salespersonquotahistory);
			//personQuotaService.update(salespersonquotahistory);
		}
		return "redirect:/salespersonquotahistory";
	}

	
	
	/** Salesterritory history mapping */
	@GetMapping("/salesterritoryhistory")
	public String salesTerritoryHistory(Model model) {
		model.addAttribute("salesterritoryhistory", businessDelegate.getSalesterritoryHistory());
		return "operator/salesterritoryhistory";
	}
	
	@GetMapping("/salesterritoryhistory/add")
	public String addSalesTerritoryHistory(Model model) {
		model.addAttribute("salesterritoryhistory", new Salesterritoryhistory());
		model.addAttribute("salespersons", businessDelegate.getSalesPerson());
		model.addAttribute("salesterritories", businessDelegate.getSalesterritory());
		return "/operator/add-salesterritoryhistory";
	}
	
	@PostMapping("/salesterritoryhistory/add")
	public String saveSalesTerritoryHistory(@Validated(SalesTerritoryHistoryValidation.class)
		@ModelAttribute Salesterritoryhistory salesterritoryhistory, BindingResult bindingResult, Model model,
		@RequestParam(value="action", required=true)String action) throws Exception {
		if(action.equals("Cancel")) {
			return "redirect:/salesterritoryhistory/";
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("salesterritoryhistory", salesterritoryhistory);
			model.addAttribute("salesperson", businessDelegate.getSalesPerson());
			model.addAttribute("salesterritory", businessDelegate.getSalesterritory());
			return "/operator/add-salesterritoryhistory";
		}else {
			this.businessDelegate.addTerritoryHistory(salesterritoryhistory);
			//territoryHistoryService.save(salesterritoryhistory);
			return "redirect:/salesterritoryhistory/";
		}
	}
	
	@GetMapping("/salesterritoryhistory/update/{id}")
	public String editSalesTerritoryHistory(@PathVariable("id")Integer id, Model model){
		//Optional<Salesterritoryhistory> tH= territoryHistoryService.findById(id);
		Salesterritoryhistory tH= businessDelegate.findByIdTerritoryHistory(id);
		if(tH.equals(null)) {
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("salesterritoryhistory", tH);
		model.addAttribute("salespersons", businessDelegate.getSalesPerson());
		model.addAttribute("salesterritories", businessDelegate.getSalesterritory());
		return "operator/update-salesterritoryhistory";
	}
	
	@PostMapping("/salesterritoryhistory/update/{id}")
	public String updateSalesTerritoryHistory(@PathVariable("id")Integer id,
			@Validated(CredentialInfoValidation.class) Salesterritoryhistory salesterritoryhistory, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) throws Exception{
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesterritoryhistory", salesterritoryhistory);
				model.addAttribute("salesperson", businessDelegate.getSalesPerson());
				model.addAttribute("salesterritory", businessDelegate.getSalesterritory());
				
				return "operator/update-salesterritoryhistory";
			}
			salesterritoryhistory.setId(id);
			businessDelegate.updateTerritoryHistory(salesterritoryhistory);
			//territoryHistoryService.update(salesterritoryhistory);
		}
		return "redirect:/salesterritoryhistory";
		
	}
}
