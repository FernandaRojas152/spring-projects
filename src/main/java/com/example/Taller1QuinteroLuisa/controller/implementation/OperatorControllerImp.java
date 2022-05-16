package com.example.Taller1QuinteroLuisa.controller.implementation;

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
import com.example.Taller1QuinteroLuisa.controller.interfaces.OperatorController;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.services.SalesPersonQuotaHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.services.SalesPersonServiceImp;
import com.example.Taller1QuinteroLuisa.services.SalesTerritoryHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.services.SalesTerritoryServiceImp;
import com.example.Taller1QuinteroLuisa.validation.CredentialInfoValidation;
import com.example.Taller1QuinteroLuisa.validation.SalesPersonQuotaHistoryValidation;
import com.example.Taller1QuinteroLuisa.validation.SalesTerritoryHistoryValidation;

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
		model.addAttribute("salespersonquotahistory", personQuotaService.findAll());
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
	
	@GetMapping("/salespersonquotahistory/update/{id}")
	public String editSalesPersonQuota(@PathVariable("id")Integer id, Model model) {
		Optional<Salespersonquotahistory> s = personQuotaService.findById(id);
		if(s.isEmpty()) {
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("salespersonquotahistory", s.get());
		model.addAttribute("salespersons", personQuotaService.findAllSalesPerson());
		return "operator/update-salespersonquotahistory";
	}
	
	@PostMapping("/salespersonquotahistory/update/{id}")
	public String updateSalesPersonQuota(@PathVariable("id")Integer id,
			@Validated(CredentialInfoValidation.class) Salespersonquotahistory salespersonquotahistory, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) throws Exception{
		if(!action.equals("Cancel")){
			if(bindingResult.hasErrors()){
				model.addAttribute("salespersonquotahistory", personQuotaService.findById(id).get());
				model.addAttribute("salesperson", personQuotaService.findAllSalesPerson());
				return "operator/update-salespersonquotahistory";
			}
			salespersonquotahistory.setBusinessentityid(id);
			personQuotaService.update(salespersonquotahistory);
		}
		return "redirect:/salespersonquotahistory";
	}
	
	/** Salesterritory history mapping */
	@GetMapping("/salesterritoryhistory")
	public String salesTerritoryHistory(Model model) {
		model.addAttribute("salesterritoryhistories", territoryHistoryService.findAll());
		return "operator/salesterritoryhistory";
	}
	
	@GetMapping("/salesterritoryhistory/add")
	public String addSalesTerritoryHistory(Model model) {
		model.addAttribute("salesterritoryhistory", new Salesterritoryhistory());
		model.addAttribute("salespersons", personService.findAll());
		model.addAttribute("salesterritories", territoryService.findAll());
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
			model.addAttribute("salesperson");
			model.addAttribute("salesterritory");
			return "/operator/add-salesterritoryhistory";
		}else {
			territoryHistoryService.save(salesterritoryhistory);
			System.out.println("aqui esta esa vaina: " + territoryHistoryService.findAll() + "valores: " + salesterritoryhistory.getSalesterritory().getName());
			System.out.println("se guarda en el modelo: " + salesterritoryhistory.getBusinessentityid() + salesterritoryhistory.getSalesterritory().getName());
			return "redirect:/salesterritoryhistory/";
		}
	}
	
//	@GetMapping("/salesterritoryhistory/update/{id}")
//	public String editSalesTerritoryHistory(@PathVariable("id")Integer id, Model model){
//		Optional<Salesterritoryhistory> tH= territoryHistoryService.findById(id);
//		if(tH.isEmpty()) {
//			throw new IllegalArgumentException("Couldn't not find the id requested");
//		}
//		model.addAttribute("salesterritoryhistory", tH.get());
//		model.addAttribute("salespersons", territoryHistoryService.findAllSalesPerson());
//		model.addAttribute("salesterritories", territoryHistoryService.findAllSalesTerritory());
//		return "operator/update-salesterritoryhistory";
//	}
//	
//	@PostMapping("/salesterritoryhistory/update/{id}")
//	public String updateSalesTerritoryHistory(@PathVariable("id")Integer id,
//			@Validated(CredentialInfoValidation.class) Salesterritoryhistory salesterritoryhistory, BindingResult bindingResult,
//			Model model, @RequestParam(value = "action", required = true) String action) throws Exception{
//		if(!action.equals("Cancel")) {
//			if(bindingResult.hasErrors()) {
//				model.addAttribute("salesterritoryhistory", territoryHistoryService.findById(id).get());
//				model.addAttribute("salesperson", territoryHistoryService.findAllSalesPerson());
//				model.addAttribute("salesterritory", territoryHistoryService.findAllSalesTerritory());
//				
//				return "operator/update-salesterritoryhistory";
//			}
//			salesterritoryhistory.setBusinessentityid(id);
//			territoryHistoryService.update(salesterritoryhistory);
//		}
//		return "redirect:/salesterritoryhistory";
//		
//	}
}
