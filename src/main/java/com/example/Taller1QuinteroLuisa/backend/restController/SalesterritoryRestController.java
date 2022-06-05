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
	
	@GetMapping("/salesterritoryRest/list")
	public Iterable<Salesterritory> getSalesTerritory(){
		return territoryService.findAll();
	}
	
	@PostMapping("/salesterritoryRest/add")
	public Salesterritory addSalesterritory(@RequestBody Salesterritory salesterritory) throws Exception {
		return territoryService.save(salesterritory);
	}
	
	@PutMapping("/salesterritoryRest/update/{id}")
	public void updateSalesterritory(@RequestBody Salesterritory salesterritory) throws Exception{
		territoryService.update(salesterritory);
	}
	
	@DeleteMapping("/salesterritoryRest/delete/{id}")
	public void deleteSalesterritory(@PathVariable("id")Integer id) {
		territoryService.delete(id);
	}
}
