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
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonQuotaHistoryServiceImp;

@RestController
@RequestMapping("/api")
public class SalespersonQuotaRestController{
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	
	@Autowired
	public SalespersonQuotaRestController(SalesPersonQuotaHistoryServiceImp personQuotaService) {
		this.personQuotaService= personQuotaService;
	}
	
	@GetMapping("/salespersonquotaRest/list")
	public Iterable<Salespersonquotahistory> getSalespersonquotahistory(){
		return personQuotaService.findAll();
	}
	
	@PostMapping("/salespersonquotaRest/add")
	public Salespersonquotahistory addSalespersonquota(@RequestBody Salespersonquotahistory salespersonquotahistory) throws Exception {
		return personQuotaService.save(salespersonquotahistory);
	}
	
	@PutMapping("/salespersonquotaRest/update/{id}")
	public void updateSalespersonquota(@RequestBody Salespersonquotahistory salespersonquotahistory) throws Exception {
		personQuotaService.update(salespersonquotahistory);
	}
	
	@DeleteMapping("/salespersonquotaRest/delete/{id}")
	public void deleteSalespersonquota(@PathVariable("id")Integer id){
		personQuotaService.delete(id);
		
	}
	

}
