package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;

public interface CurrencyRateService{
	public void saveCurrencyRate(Currencyrate currencyrate);
	public void updateCurrencyRate(Currencyrate currencyrate);
	public void deleteCurrencyRate(Integer id);
	public Currencyrate findById(Integer id);
	public Iterable<Currencyrate> findAll();
	

}
