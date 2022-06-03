package com.example.Taller1QuinteroLuisa.frontend.businessdelegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;

import lombok.Getter;
import lombok.Setter;

@Component
public class BusinessDelegate {
	@Getter
	@Setter
	private RestTemplate restTemplate;
	
	private final String URL= "http://localhost:8080/api/";
	
	public BusinessDelegate() {
		this.restTemplate= new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
	}
	
	/** DELEGATE CLASSES */
	//Salesperson
	public Iterable<Salesperson> findAllPerson(){
		return null;
	}
	

}
