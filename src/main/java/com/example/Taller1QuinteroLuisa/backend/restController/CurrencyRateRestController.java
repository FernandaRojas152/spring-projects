<<<<<<< HEAD
package com.example.Taller1QuinteroLuisa.backend.restController;

public class CurrencyRateRestController {

}
=======
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

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;
import com.example.Taller1QuinteroLuisa.backend.services.CurrencyRateServiceImp;


@RestController
@RequestMapping("/api")
public class CurrencyRateRestController{
	private CurrencyRateServiceImp currencyrateService;
	
	@Autowired
	public CurrencyRateRestController(CurrencyRateServiceImp currencyrateService) {
		this.currencyrateService= currencyrateService;
	}
	
	@GetMapping("/currencyrateRest/list")
	public Iterable<Currencyrate> getCurrencyRate(){
		return currencyrateService.findAll();
	}
	
	@PostMapping("/currencyrateRest/add")
	public void addCurrencyrate(@RequestBody Currencyrate currencyrate) {
		currencyrateService.saveCurrencyRate(currencyrate);
	}
	
	@PutMapping("/currencyrateRest/update/{id}")
	public void updateCurrencyrate(@RequestBody Currencyrate currencyrate) {
		currencyrateService.updateCurrencyRate(currencyrate);
	}
	
	@DeleteMapping("/currencyrateRest/delete/{id}")
	public void deleteCurrencyrate(@PathVariable("id")Integer id) {
		currencyrateService.deleteCurrencyRate(id);
	}
	
	@GetMapping("/currencyrateRest/list/{id}")
	public Currencyrate findById(@PathVariable("id")Integer id) {
		return currencyrateService.findById(id);
	}

}
>>>>>>> b3620eaf0c7fe27cfe43b25719cfd0bac11d1364
