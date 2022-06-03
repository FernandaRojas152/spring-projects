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

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonQuotaHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.validation.CredentialInfoValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.SalesPersonValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.SalesTerritoryValidation;

@Controller
public class AdministratorControllerImp {
	private SalesPersonServiceImp personService;
	private SalesTerritoryServiceImp territoryService;
	
	@Autowired
	private SalesTerritoryHistoryServiceImp territoryHistoryService;
	@Autowired
	private SalesPersonQuotaHistoryServiceImp personQuotaService;

	@Autowired
	public AdministratorControllerImp(SalesPersonServiceImp personService, SalesTerritoryServiceImp territoryService) {
		this.personService = personService;
		this.territoryService = territoryService;
	}

	@GetMapping("/administrator")
	public String admin(Model model) {
		// model.addAttribute("salespersons", personService.findAll()); return
		return "administrator/administrator";
	}

	/** Salesperson mapping */

	@GetMapping("/salesperson")
	public String salesperson(Model model) {
		model.addAttribute("salesperson", personService.findAll());
		return "administrator/salesperson";
	}

	@GetMapping("/salesperson/add")
	public String addSalesPerson(Model model) {
		model.addAttribute("salesperson", new Salesperson());
		model.addAttribute("salesterritories", territoryService.findAll());
		return "administrator/add-salesperson";
	}

	@PostMapping("/salesperson/add")
	public String saveSalesPerson(@Validated(SalesPersonValidation.class) @ModelAttribute Salesperson salesperson,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action)
			throws Exception {
		if (action.equals("Cancel")) {
			return "redirect:/salesperson/";
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("salesperson", salesperson);
			model.addAttribute("salesterritory");
			return "/administrator/add-salesperson";
		} else {
			personService.save(salesperson);
			return "redirect:/salesperson/";
		}
	}
	
	@GetMapping("/salesperson/update/{id}")
	public String editSalesPerson(@PathVariable("id")Integer id, Model model){
		Optional<Salesperson> p= personService.findById(id);
		if(p.isEmpty()){
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("salesperson", p.get());
		model.addAttribute("salesterritories", personService.findAllTerritories());
		
		return "administrator/update-salesperson";
	}
	
	//"@{/salesperson/update/{id}(id=${salesperson.businessentityid})}"
	@PostMapping("/salesperson/update/{id}")
	public String updateSalesPerson(@PathVariable("id")Integer id,
			@Validated(CredentialInfoValidation.class) Salesperson salesperson, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) throws Exception{
		if(!action.equals("Cancel")){
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesperson", personService.findById(id).get());
				model.addAttribute("salesterritory", personService.findAllTerritories());
				return "administrator/update-salesperson";
			}
			salesperson.setBusinessentityid(id);
			personService.update(salesperson);
		}
		return "redirect:/salesperson/";
	}

	/** Salesterritory mapping */

	@GetMapping("/salesterritory")
	public String salesTerritory(Model model) {
		model.addAttribute("salesterritory", territoryService.findAll());
		return "administrator/salesterritory";
	}

	@GetMapping("/salesterritory/add")
	public String addSalesTerritory(Model model) {
		model.addAttribute("salesterritory", new Salesterritory());
		return "administrator/add-salesterritory";
	}

	@PostMapping("/salesterritory/add")
	public String saveSalesterritory(
			@Validated(SalesTerritoryValidation.class) @ModelAttribute Salesterritory salesterritory,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action)
			throws Exception {
		if (action.equals("Cancel")) {
			return "redirect:/salesterritory/";
		}
		if (bindingResult.hasErrors()) {
			return "/administrator/add-salesterritory";
		} else {
			territoryService.save(salesterritory);
			return "redirect:/salesterritory/";
		}
	}
	
	@GetMapping("/salesterritory/update/{id}")
	public String editSalesTerritory(@PathVariable("id")Integer id, Model model) {
		Optional<Salesterritory> t= territoryService.findById(id);
		if(t.isEmpty()) {
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("salesterritory", t.get());
		return "administrator/update-salesterritory";
	}
	
	@PostMapping("/salesterritory/update/{id}")
	public String updateSalesTerritory(@PathVariable("id") Integer id,
			@Validated(CredentialInfoValidation.class) Salesterritory salesterritory, BindingResult bindingResult, Model model,
			@RequestParam(value="action", required= true) String action) throws Exception{
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesterritory", territoryService.findById(id).get());
				return "administrator/update-salesterritory";
			}
			salesterritory.setTerritoryid(id);
			territoryService.update(salesterritory);
		}
		return "redirect:/salesterritory";
	}
	
	//Parent to child

	@GetMapping("/salesperson/{id}")
	public String querySalesPersons(@PathVariable("id")Integer id, Model model) {
		model.addAttribute("salesperson", personService.findByTerritory(id));
		return "administrator/salesperson-query";
	}
	
	@GetMapping("/salesterritoryhistory/{id}")
	public String querySalesTerritoryHistory(@PathVariable("id")Integer id, Model model) {
		model.addAttribute("salesterritoryhistory", territoryHistoryService.findByTerritory(id));
		return "administrator/salesterritoryhistory-query";
	}
	
	@GetMapping("/salespersonquotahistory/{id}")
	public String querySalesPersonQuota(@PathVariable("id")Integer id, Model model){
		model.addAttribute("salespersonquotahistory", personQuotaService.findBySalesPerson(id));
		return "administrator/salesquotahistory-query";
	}
}
