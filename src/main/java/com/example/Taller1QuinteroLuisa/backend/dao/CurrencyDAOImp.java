package com.example.Taller1QuinteroLuisa.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;

@Repository
@Scope("singleton")
public class CurrencyDAOImp implements CurrencyDAO{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void save(Currency currency) {
		entityManager.persist(currency);	
	}

	@Transactional
	@Override
	public void update(Currency currency) {
		entityManager.merge(currency);
	}

	@Override
	public void delete(Currency currency) {
		entityManager.remove(currency);
	}

	@Override
	public List<Currency> findAll() {
		Query query= entityManager.createQuery("SELECT c FROM Currency c");
		return query.getResultList();
	}

	@Override
	public Currency findById(String id) {
		return entityManager.find(Currency.class, id);
	}

	@Override
	public void deleteAll() {
		Query query= entityManager.createQuery("DELETE FROM Currency");
		query.executeUpdate();	
	}
}
