package com.example.Taller1QuinteroLuisa.frontend.businessdelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.restController.CurrencyRateRestController;
import com.example.Taller1QuinteroLuisa.backend.restController.CurrencyRestController;
import com.example.Taller1QuinteroLuisa.backend.restController.SalespersonQuotaRestController;
import com.example.Taller1QuinteroLuisa.backend.restController.SalespersonRestController;
import com.example.Taller1QuinteroLuisa.backend.restController.SalesterritoryHistoryRestController;
import com.example.Taller1QuinteroLuisa.backend.restController.SalesterritoryRestController;

import lombok.Getter;
import lombok.Setter;

@Component
public class BusinessDelegate {
	@Getter
	@Setter
	private RestTemplate restTemplate;
	
	private SalespersonRestController personRest;
	private SalesterritoryRestController territoryRest;
	private SalespersonQuotaRestController personquotaRest;
	private SalesterritoryHistoryRestController territoryHistoryRest;
	
	
	private CurrencyRestController currencyRest;
	private CurrencyRateRestController currencyrateRest;
	
	
	private final String URLPERSON= "http://localhost:8080/api/salespersonRest/list";
	private final String URLPERSONQUOTA= "http://localhost:8080/api/salespersonRest/list";
	private final String URLTERRITORYHISTORY= "http://localhost:8080/api/salespersonRest/list";
	private final String URLCURRENCY= "http://localhost:8080/api/salespersonRest/list";
	private final String URLCURRENCYRATE= "http://localhost:8080/api/salespersonRest/list";
	
	public BusinessDelegate() {
		this.restTemplate= new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
	}
	
	/** DELEGATE CLASSES FROM PREVIOUS WORK*/
	//Salesperson
	
	public List<Salesperson> getSalesPerson() {
		Salesperson[] personArray= restTemplate.getForObject(URLPERSON, Salesperson[].class);
		return Arrays.asList(personArray);
	}
	
	public Salesperson addSalesperson(Salesperson salesperson) {
		return restTemplate.postForObject(URLPERSON, salesperson, Salesperson.class);
	}
	
	public void updateSalesperson(Salesperson salesperson){
		restTemplate.put(URLPERSON+salesperson.getBusinessentityid(), salesperson, Salesperson.class);
	}
	
	public void delete(Integer id){
		restTemplate.delete(URLPERSON+id);
	}
	
	public Salesperson findById(Integer id) {
		return restTemplate.getForObject(URLPERSON+id, Salesperson.class);
	}
	
	
	
	/** DELEGATE CLASSES FOR FINAL PROJECT*/

}
