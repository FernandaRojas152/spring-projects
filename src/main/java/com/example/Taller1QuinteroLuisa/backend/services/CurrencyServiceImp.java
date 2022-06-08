package com.example.Taller1QuinteroLuisa.backend.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taller1QuinteroLuisa.backend.dao.CurrencyDAOImp;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;

@Service
@Transactional
public class CurrencyServiceImp implements CurrencyService{
	private CurrencyDAOImp currencyDao;
	
	@Autowired
	public CurrencyServiceImp(CurrencyDAOImp currencyDao) {
		this.currencyDao= currencyDao;
	}

	@Override
	public void saveCurrency(Currency currency) {
		this.currencyDao.save(currency);
	}

	@Override
	public void updateCurrency(Currency currency) {
		this.currencyDao.update(currency);
	}

	@Override
	public void deleteCurrency(Integer id) {
		this.currencyDao.delete(currencyDao.findById(id));
	}

	@Override
	public Currency findById(Integer id) {
		return currencyDao.findById(id);
	}

	@Override
	public Iterable<Currency> findAll() {
		return currencyDao.findAll();
	}

}
