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
	
	@GetMapping("/currency/")
	public Iterable<Currency> getCurrency(){
		return currencyService.findAll();
	}
	
	@PostMapping("/currency/")
	public void addCurrency(@RequestBody Currency currency) {
		currencyService.saveCurrency(currency);
	}
	
	@PutMapping("/currency/{id}")
	public void updateCurrency(@RequestBody Currency currency) {
		currencyService.updateCurrency(currency);
	}
	
	@DeleteMapping("/currency/{id}")
	public void deleteCurrency(@PathVariable("id")Integer id) {
		currencyService.deleteCurrency(id);
	}
	
	@GetMapping("/currency/{id}")
	public Currency findById(@PathVariable("id")Integer id) {
		return currencyService.findById(id);
	}
}

