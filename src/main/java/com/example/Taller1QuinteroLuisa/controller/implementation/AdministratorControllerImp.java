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

import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.services.SalesPersonServiceImp;
import com.example.Taller1QuinteroLuisa.services.SalesTerritoryServiceImp;
import com.example.Taller1QuinteroLuisa.validation.SalesPersonValidation;
import com.example.Taller1QuinteroLuisa.validation.SalesTerritoryValidation;

@Controller
public class AdministratorControllerImp {
	private SalesPersonServiceImp personService;
	private SalesTerritoryServiceImp territoryService;

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
			model.addAttribute("salesterritories");
			return "/administrator/add-salesperson";
		} else {
			personService.save(salesperson);
			return "redirect:/salesperson/";
		}
	}

	/** Salesterritory mapping */

	@GetMapping("/salesterritory")
	public String salesTerritory(Model model) {
		model.addAttribute("salesterritories", territoryService.findAll());
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
}
