package com.example.Taller1QuinteroLuisa.backend.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;
import com.example.Taller1QuinteroLuisa.backend.services.CurrencyServiceImp;

@RestController
@RequestMapping("/api")
public class CurrencyRestController{
	private CurrencyServiceImp currencyService;
	
	@Autowired
	public CurrencyRestController(CurrencyServiceImp currencyService) {
		this.currencyService= currencyService;
	}
	
	@GetMapping("/currencyRest/list")
	public Iterable<Currency> getCurrency(){
		return currencyService.findAll();
	}
	
	@PostMapping("/currencyRest/add")
	public void addCurrency(@RequestBody Currency currency) {
		currencyService.saveCurrency(currency);
	}
	
	@PutMapping("/currencyRest/update/{id}")
	public void updateCurrency(@RequestBody Currency currency) {
		currencyService.updateCurrency(currency);
	}
	
	@DeleteMapping("/currencyRest/delete/{id}")
	public void deleteCurrency(@PathVariable("id")String id) {
		currencyService.deleteCurrency(id);
	}
	
	@GetMapping("/currencyRest/list/{id}")
	public Currency findById(@PathVariable("id")String id) {
		return currencyService.findById(id);
	}
}

