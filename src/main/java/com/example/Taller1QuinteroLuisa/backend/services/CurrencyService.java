<<<<<<< HEAD
package com.example.Taller1QuinteroLuisa.backend.services;

import java.time.LocalDate;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;

public interface CurrencyService {
	public void saveCurrency(Currency currency);
	public void updateCurrency(Currency currency);
	public void deleteCurrency(String id);
	public Currency findById(String id);
	public Iterable<Currency> findAll();
//	public Iterable<Currency> findByStartDate(LocalDate startdate);
//	public Iterable<Currency> findByEndDate(LocalDate enddate);
}
=======
package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;

public interface CurrencyService {
	public void saveCurrency(Currency currency);
	public void updateCurrency(Currency currency);
	public void deleteCurrency(String id);
	public Currency findById(String id);
	public Iterable<Currency> findAll();
}
>>>>>>> b3620eaf0c7fe27cfe43b25719cfd0bac11d1364
