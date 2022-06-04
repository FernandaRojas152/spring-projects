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
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;
import lombok.Getter;
import lombok.Setter;

@Component
public class BusinessDelegate {
	@Getter
	@Setter
	private RestTemplate restTemplate;
	
	
	private final String URLPERSON= "http://localhost:8080/api/salespersonRest/list";
	private final String URLPERSONQUOTA= "http://localhost:8080/api/salespersonquotaRest/list";
	private final String URLTERRITORY= "http://localhost:8080/api/salesterritoryRest/list";
	private final String URLTERRITORYHISTORY= "http://localhost:8080/api/salesterritoryhistoryRest/list";
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
	
	public void deleteSalesperson(Integer id){
		restTemplate.delete(URLPERSON+id);
	}
	
	public Salesperson findByIdSalesperson(Integer id) {
		return restTemplate.getForObject(URLPERSON+id, Salesperson.class);
	}
	
	//Salesterritory
	public List<Salesterritory> getSalesterritory(){
		Salesterritory[] territoryArray= restTemplate.getForObject(URLTERRITORY, Salesterritory[].class);
		return Arrays.asList(territoryArray);
	}
	
	public Salesterritory addSalesterritory(Salesterritory salesterritory) {
		return restTemplate.postForObject(URLTERRITORY, salesterritory, Salesterritory.class);
	}
	
	public void updateSalesterritory(Salesterritory salesterritory) {
		restTemplate.put(URLTERRITORY+salesterritory.getTerritoryid(), salesterritory, Salesterritory.class);
	}
	
	public void deleteSalesterritory(Integer id) {
		restTemplate.delete(URLTERRITORY+id);
	}
	
	public Salesterritory findByIdTerritory(Integer id) {
		return restTemplate.getForObject(URLTERRITORY+id, Salesterritory.class);
	}
	
	//Salespersonquotahistory
	public List<Salespersonquotahistory> getSalespersonQuota(){
		Salespersonquotahistory[] personquotaArray= restTemplate.getForObject(URLPERSONQUOTA, Salespersonquotahistory[].class);
		return Arrays.asList(personquotaArray);
	}
	
	public Salespersonquotahistory addPersonQuota(Salespersonquotahistory salespersonquotahistory) {
		return restTemplate.postForObject(URLPERSONQUOTA, salespersonquotahistory, Salespersonquotahistory.class);
	}
	
	public void updatePersonQuota(Salespersonquotahistory salespersonquotahistory) {
		restTemplate.put(URLPERSONQUOTA+salespersonquotahistory.getBusinessentityid(), salespersonquotahistory, Salespersonquotahistory.class);
	}
	
	public void deletePersonQuota(Integer id) {
		restTemplate.delete(URLPERSONQUOTA+id);
	}
	
	public Salespersonquotahistory findByIdPersonQuota(Integer id){
		return restTemplate.getForObject(URLPERSONQUOTA+id, Salespersonquotahistory.class);
	}
	
	//Salesterritoryhistory
	public List<Salesterritoryhistory> getSalesterritoryHistory(){
		Salesterritoryhistory[] territoryhistoryArray= restTemplate.getForObject(URLTERRITORYHISTORY, Salesterritoryhistory[].class);
		return Arrays.asList(territoryhistoryArray);
	}
	
	public Salesterritoryhistory addTerritoryHistory(Salesterritoryhistory salesterritoryhistory){
		return restTemplate.postForObject(URLTERRITORYHISTORY, salesterritoryhistory, Salesterritoryhistory.class);
	}
	
	public void updateTerritoryHistory(Salesterritoryhistory salesterritoryhistory) {
		restTemplate.put(URLTERRITORYHISTORY+salesterritoryhistory.getId(), salesterritoryhistory, Salesterritoryhistory.class);
	}
	
	public void deleteTerritoryHistory(Integer id) {
		restTemplate.delete(URLTERRITORYHISTORY+id);
	}
	
	public Salesterritoryhistory findByIdTerritoryHistory(Integer id){
		return restTemplate.getForObject(URLTERRITORYHISTORY+id, Salesterritoryhistory.class);
	}
	
	/** DELEGATE CLASSES FOR FINAL PROJECT*/
	//Currency
	public List<Currency> getCurrency(){
		Currency[] currencyArray= restTemplate.getForObject(URLCURRENCY, Currency[].class);
		return Arrays.asList(currencyArray);
	}
	
	public Currency addCurrency(Currency currency) {
		return restTemplate.postForObject(URLCURRENCY, currency, Currency.class);
	}
	
	public void updateCurrency(Currency currency) {
		restTemplate.put(URLCURRENCY+currency.getCurrencycode(), currency, Currency.class);
	}
	
	public void deleteCurrency(String id) {
		restTemplate.delete(URLCURRENCY+id);
	}
	
	public Currency findbyIdCurrency(String id) {
		return restTemplate.getForObject(URLCURRENCY+id, Currency.class);
	}
	
	//Currencyrate
	public List<Currencyrate> getCurrencyrate(){
		Currencyrate[] currencyrateArray= restTemplate.getForObject(URLCURRENCYRATE, Currencyrate[].class);
		return Arrays.asList(currencyrateArray);
	}
	
	public Currencyrate addCurrencyrate(Currencyrate currencyrate) {
		return restTemplate.postForObject(URLCURRENCYRATE, currencyrate, Currencyrate.class);
	}
	
	public void updateCurrencyrate(Currencyrate currencyrate) {
		restTemplate.put(URLCURRENCYRATE+currencyrate.getCurrencyrateid(), currencyrate, Currencyrate.class);
	}
	
	public void deleteCurrencyrate(Integer id) {
		restTemplate.delete(URLCURRENCYRATE+id);
	}
	
	public Currencyrate findbyIdCurrencyRate(Integer id) {
		return restTemplate.getForObject(URLCURRENCYRATE+id, Currencyrate.class);
	}

}
