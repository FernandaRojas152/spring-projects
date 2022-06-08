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

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonQuotaHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryHistoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryServiceImp;
import com.example.Taller1QuinteroLuisa.backend.validation.CredentialInfoValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.CurrencyRateValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.CurrencyValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.SalesPersonValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.SalesTerritoryValidation;
import com.example.Taller1QuinteroLuisa.frontend.businessdelegate.BusinessDelegate;

import lombok.extern.java.Log;

@Controller
public class AdministratorControllerImp {
	
	private SalesPersonServiceImp personService;
	private SalesTerritoryServiceImp territoryService;
	
	@Autowired
	private SalesTerritoryHistoryServiceImp territoryHistoryService;
	@Autowired
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	
	@Autowired
	private BusinessDelegate businessDelegate;
	
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
		model.addAttribute("salesperson", businessDelegate.getSalesPerson());
		//model.addAttribute("salesperson", personService.findAll());
		return "administrator/salesperson";
	}

	@GetMapping("/salesperson/add")
	public String addSalesPerson(Model model) {
		model.addAttribute("salesperson", new Salesperson());
		model.addAttribute("salesterritories", businessDelegate.getSalesterritory());
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
			model.addAttribute("salesterritory", businessDelegate.getSalesterritory());
			return "/administrator/add-salesperson";
		} else {
			//personService.save(salesperson);
			this.businessDelegate.addSalesperson(salesperson);
			return "redirect:/salesperson/";
		}
	}
	
	@GetMapping("/salesperson/update/{id}")
	public String editSalesPerson(@PathVariable("id")Integer id, Model model){
		//Optional<Salesperson> p= personService.findById(id);
		Salesperson p= businessDelegate.findByIdSalesperson(id);
		if(p.equals(null)){
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("salesperson", p);
		model.addAttribute("salesterritories", businessDelegate.getSalesterritory());
		
		return "administrator/update-salesperson";
	}
	
	//"@{/salesperson/update/{id}(id=${salesperson.businessentityid})}"
	@PostMapping("/salesperson/update/{id}")
	public String updateSalesPerson(@PathVariable("id")Integer id,
			@Validated(CredentialInfoValidation.class) Salesperson salesperson, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) throws Exception{
		if(!action.equals("Cancel")){
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesperson", salesperson);
				model.addAttribute("salesterritory", businessDelegate.getSalesterritory());
				return "administrator/update-salesperson";
			}
			salesperson.setBusinessentityid(id);
			//personService.update(salesperson);
			businessDelegate.updateSalesperson(salesperson);
		}
		return "redirect:/salesperson/";
	}

	/** Salesterritory mapping */

	@GetMapping("/salesterritory")
	public String salesTerritory(Model model) {
		//model.addAttribute("salesterritory", territoryService.findAll());
		model.addAttribute("salesterritory", businessDelegate.getSalesterritory());
		return "administrator/salesterritory";
	}

	@GetMapping("/salesterritory/add")
	public String addSalesTerritory(Model model){
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
			this.businessDelegate.addSalesterritory(salesterritory);
			//this.territoryService.save(salesterritory);
			return "redirect:/salesterritory/";		
		}
	}
	
	@GetMapping("/salesterritory/update/{id}")
	public String editSalesTerritory(@PathVariable("id")Integer id, Model model) {
		//Optional<Salesterritory> t= territoryService.findById(id);
		Salesterritory t = businessDelegate.findByIdTerritory(id);
		if(t.equals(null)) {
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("salesterritory", t);
		return "administrator/update-salesterritory";
	}
	
	@PostMapping("/salesterritory/update/{id}")
	public String updateSalesTerritory(@PathVariable("id") Integer id,
			@Validated(CredentialInfoValidation.class) Salesterritory salesterritory, BindingResult bindingResult, Model model,
			@RequestParam(value="action", required= true) String action) throws Exception{
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesterritory", salesterritory);
				return "administrator/update-salesterritory";
			}
			salesterritory.setTerritoryid(id);
			//territoryService.update(salesterritory);
			businessDelegate.updateSalesterritory(salesterritory);
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
	
	
	//New classes
	/** Currency Mapping */
	@GetMapping("/currency")
	public String currency(Model model) {
		model.addAttribute("currency", businessDelegate.getCurrency());
		return "administrator/currency";
	}
	
	@GetMapping("/currency/add")
	public String addCurrency(Model model) {
		model.addAttribute("currency", new Currency());
		return "administrator/add-currency";
	}

	@PostMapping("/currency/add")
	public String saveCurrency(@Validated(CurrencyValidation.class) @ModelAttribute Currency currency,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action){
		if(action.equals("Cancel")) {
			return "redirect:/currency/";
		}
		if(bindingResult.hasErrors()) {
			return "/administrator/add-currency";
		}else {
			this.businessDelegate.addCurrency(currency);
			return "redirect:/currency/";
		}
	}
	
	@GetMapping("/currency/update/{id}")
	public String editCurrency(@PathVariable("id")Integer id, Model model){
		Currency t= businessDelegate.findbyIdCurrency(id);
		if(t.equals(null)) {
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("currency", t);
		return "administrator/update-currency";
	}

	@GetMapping("/currency/delete/{id}")
	public String deleteCurrency(@PathVariable("id")Integer id, Model model){
		//Currency currency = businessDelegate.findbyIdCurrency(id);
		businessDelegate.deleteCurrency(id);
		model.addAttribute("currency", businessDelegate.getCurrency());
		return "administrator/currency";
	}

	@PostMapping("/currency/update/{id}")
	public String updateCurrency(@PathVariable("id") Integer id, @Validated(CredentialInfoValidation.class) Currency currency,
			BindingResult bindingResult, Model model, @RequestParam(value="action", required= true) String action){
		if(!action.equals("Cancel")){
			if(bindingResult.hasErrors()) {
				model.addAttribute("currency", currency);
				return "administrator/update-currency";
			}
			currency.setCurrencycode(id);
			businessDelegate.updateCurrency(currency);
		}
		return "redirect:/currency";
	}
	
	/**Currency rate Mapping*/
	@GetMapping("/currencyrate")
	public String currencyRate(Model model) {
		model.addAttribute("currencyrate", businessDelegate.getCurrencyrate());
		return "administrator/currencyrate";
	}
	
	@GetMapping("/currencyrate/add")
	public String addCurrencyRate(Model model) {
		model.addAttribute("currencyrate", new Currencyrate());
		model.addAttribute("currencies", businessDelegate.getCurrency());
		return "administrator/add-currencyrate";
	}

	@PostMapping("/currencyrate/add")
	public String saveCurrencyRate(@Validated(CurrencyRateValidation.class) @ModelAttribute Currencyrate currencyrate,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(action.equals("Cancel")){
			return "redirect:/currencyrate/";
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("currencyrate", currencyrate);
			model.addAttribute("currencies", businessDelegate.getCurrency());
			return "/administrator/add-currencyrate";
		}else {
			this.businessDelegate.addCurrencyrate(currencyrate);
			return "redirect:/currencyrate/";
		}
	}
	
	@GetMapping("/currencyrate/update/{id}")
	public String editCurrencyRate(@PathVariable("id")Integer id, Model model) {
		Currencyrate t= businessDelegate.findbyIdCurrencyRate(id);
		if(t.equals(null)){
			throw new IllegalArgumentException("Couldn't not find the id requested");
		}
		model.addAttribute("currencyrate", t);
		model.addAttribute("currencies", businessDelegate.getCurrency());
		return "administrator/update-currencyrate";
		
	}
	
	@PostMapping("/currencyrate/update/{id}")
	public String updateCurrencyRate(@PathVariable("id")Integer id, @Validated(CredentialInfoValidation.class) Currencyrate currencyrate,
			BindingResult bindingResult, Model model, @RequestParam(value="action", required= true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("currencyrate", currencyrate);
				model.addAttribute("currency", businessDelegate.getCurrency());
				return "administrator/update-currencyrate";
			}
			
			currencyrate.setCurrencyrateid(id);
			businessDelegate.updateCurrencyrate(currencyrate);
		}
		return "redirect:/currencyrate";
	}
	
	//Special Queries
	
//	@GetMapping("/query")
//	public String specialQueries(Model model){
//		model.addAttribute("salesterritory", businessDelegate.findTwoSalesPersonWithQuota());
//		return "administrator/query";
//	}
}
