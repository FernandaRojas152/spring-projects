<<<<<<< HEAD
package com.example.Taller1QuinteroLuisa.backend.services;

public interface CurrencyRateServiceImp {

}
=======
package com.example.Taller1QuinteroLuisa.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.backend.dao.CurrencyRateDAOImp;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;

@Service
public class CurrencyRateServiceImp implements CurrencyRateService{
	private CurrencyRateDAOImp currencyrateDao;
	
	@Autowired
	public CurrencyRateServiceImp(CurrencyRateDAOImp currencyrateDao) {
		this.currencyrateDao= currencyrateDao;
	}

	@Override
	public void saveCurrencyRate(Currencyrate currencyrate) {
		this.currencyrateDao.save(currencyrate);
	}

	@Override
	public void updateCurrencyRate(Currencyrate currencyrate) {
		this.currencyrateDao.update(currencyrate);
	}

	@Override
	public void deleteCurrencyRate(Integer id) {
		this.currencyrateDao.delete(currencyrateDao.findById(id));
	}

	@Override
	public Currencyrate findById(Integer id) {
		return currencyrateDao.findById(id);
	}

	@Override
	public Iterable<Currencyrate> findAll() {
		return currencyrateDao.findAll();
	}
}
>>>>>>> b3620eaf0c7fe27cfe43b25719cfd0bac11d1364
