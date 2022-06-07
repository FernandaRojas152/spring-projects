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
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonServiceImp;

@RestController
@RequestMapping("/api")
public class SalespersonRestController{
	private SalesPersonServiceImp personService;
	
	@Autowired
	public SalespersonRestController(SalesPersonServiceImp personService) {
		this.personService= personService;
	}
	
	@GetMapping("/salesperson/")
	public Iterable<Salesperson> getSalesPerson(){
		return personService.findAll();
	}
	
	@PostMapping("/salesperson/")
	public Salesperson addSalesperson(@RequestBody Salesperson salesperson) throws Exception {
		return personService.save(salesperson);	
	}
	
	@PutMapping("/salesperson/{id}")
	public void updateSalesPerson(@RequestBody Salesperson salesperson) throws Exception{
		personService.update(salesperson);
	}
	
	@DeleteMapping("/salesperson/{id}")
	public void deleteSalesPerson(@PathVariable("id")Integer id) {
		personService.delete(id);
	}
	
	@GetMapping("/salesperson/{id}")
    public Salesperson getById(@PathVariable("id") Integer id) {
		return personService.findById(id).get();
	}
}
