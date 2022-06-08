package com.example.Taller1QuinteroLuisa.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taller1QuinteroLuisa.backend.dao.CurrencyDAOImp;
import com.example.Taller1QuinteroLuisa.backend.dao.CurrencyRateDAOImp;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;

@Service
@Transactional
public class CurrencyRateServiceImp implements CurrencyRateService{

	private CurrencyRateDAOImp currencyrateDao;
	private CurrencyDAOImp currencyDAOImp;

	@Autowired
	public CurrencyRateServiceImp(CurrencyRateDAOImp currencyrateDao, CurrencyDAOImp currencyDAOImp) {
		this.currencyrateDao= currencyrateDao;
		this.currencyDAOImp = currencyDAOImp;
	}

	@Override
	public void saveCurrencyRate(Currencyrate currencyrate) {
		Currency currency1 = this.currencyDAOImp.findById(currencyrate.getCurrency1().getCurrencycode());
		Currency currency2 = this.currencyDAOImp.findById(currencyrate.getCurrency2().getCurrencycode());
		if (!currency1.equals(null) && !currency2.equals(null)) {
			currencyrate.setCurrency1(currency1);
			currencyrate.setCurrency2(currency2);
			currencyrateDao.save(currencyrate);
		}
		//this.currencyrateDao.save(currencyrate);
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
