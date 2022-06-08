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

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryHistoryServiceImp;

@RestController
@RequestMapping("/api")
public class SalesterritoryHistoryRestController{
	private SalesTerritoryHistoryServiceImp territoryHistoryService;
	
	@Autowired
	public SalesterritoryHistoryRestController(SalesTerritoryHistoryServiceImp territoryHistoryService) {
		this.territoryHistoryService=territoryHistoryService;
	}
	
	@GetMapping("/salesterritoryhistory/")
	public Iterable<Salesterritoryhistory> getTerritoryHistory(){
		return territoryHistoryService.findAll();
	}
	
	@PostMapping("/salesterritoryhistory/")
	public Salesterritoryhistory addTerritoryHistory(@RequestBody Salesterritoryhistory salesterritoryhistory) throws Exception{
		return territoryHistoryService.save(salesterritoryhistory);
	}
	
	@PutMapping("/salesterritoryhistory/{id}")
	public void updateTerritoryHistory(@RequestBody Salesterritoryhistory salesterritoryhistory) throws Exception{
		territoryHistoryService.update(salesterritoryhistory);
	}

	@GetMapping("/salesterritoryhistory/{id}")
	public Salesterritoryhistory getById(@PathVariable("id") Integer id){
		return territoryHistoryService.findById(id).get();
	}

	@DeleteMapping("/salesterritoryhistory/{id}")
	public void deleteTerritoryHistory(@PathVariable("id")Integer id){
		territoryHistoryService.delete(id);
	}

}
