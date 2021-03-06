package com.example.Taller1QuinteroLuisa.backend.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryServiceImp;

@RestController
@RequestMapping("/api")
public class SalesterritoryRestController{

	private SalesTerritoryServiceImp territoryService;
	
	@Autowired
	public SalesterritoryRestController(SalesTerritoryServiceImp territoryService) {
		this.territoryService= territoryService;
	}
	
	@GetMapping("/salesterritory/")
	public Iterable<Salesterritory> getSalesTerritory(){
		return territoryService.findAll();
	}
	
	@PostMapping("/salesterritory/")
	public Salesterritory addSalesterritory(@RequestBody Salesterritory salesterritory) throws Exception {
		return territoryService.save(salesterritory);
		// HttpHeaders headers =  new HttpHeaders();
		// headers.add("Reader", "SalesterritoryRestController");
		// //log.info("Post Create Student : " + salesterritory);
		// return new ResponseEntity<Salesterritory>(salesterritory, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/salesterritory/{id}")
	public void updateSalesterritory(@RequestBody Salesterritory salesterritory) throws Exception{
		territoryService.update(salesterritory);
	}

	@GetMapping("/salesterritory/{id}")
    public Salesterritory getById(@PathVariable("id") Integer id) {
		return territoryService.findById(id).get();
	}
	
	@DeleteMapping("/salesterritory/{id}")
	public void deleteSalesterritory(@PathVariable("id")Integer id) {
		territoryService.delete(id);
	}
}
