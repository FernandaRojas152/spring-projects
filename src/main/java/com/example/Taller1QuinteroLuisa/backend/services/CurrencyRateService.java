<<<<<<< HEAD
package com.example.Taller1QuinteroLuisa.backend.services;

public interface CurrencyRateService {

}
=======
package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;

public interface CurrencyRateService{
	public void saveCurrencyRate(Currencyrate currencyrate);
	public void updateCurrencyRate(Currencyrate currencyrate);
	public void deleteCurrencyRate(Integer id);
	public Currencyrate findById(Integer id);
	public Iterable<Currencyrate> findAll();
	

}
>>>>>>> b3620eaf0c7fe27cfe43b25719cfd0bac11d1364
