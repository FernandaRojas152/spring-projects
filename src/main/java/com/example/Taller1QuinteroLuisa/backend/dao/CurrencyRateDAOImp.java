package com.example.Taller1QuinteroLuisa.backend.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;

@Repository
@Scope("singleton")
@Transactional
public class CurrencyRateDAOImp implements CurrencyRateDAO{
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	//@Transactional
	@Override
	public void save(Currencyrate currencyRate) {
		entityManager.persist(currencyRate);
	}

	@Override
	public void update(Currencyrate currencyRate) {
		entityManager.merge(currencyRate);
	}

	@Override
	public void delete(Currencyrate currencyRate) {
		entityManager.remove(currencyRate);
	}

	@Override
	public List<Currencyrate> findAll() {
		Query query= entityManager.createQuery("SELECT c FROM Currencyrate c");
		return query.getResultList();
	}

	@Override
	@Transactional
	public Currencyrate findById(Integer id) {
		return entityManager.find(Currencyrate.class, id);
	}

	@Override
	public void deleteAll() {
		Query query= entityManager.createQuery("DELETE FROM Currencyrate");
		query.executeUpdate();
	}

	@Override
	public List<Currencyrate> findByCurrency(Integer currencyid) {
		String jpql= "SELECT c FROM Currencyrate c WHERE c.currency1.currencyid = '"+currencyid+"'";
		return entityManager.createQuery(jpql, Currencyrate.class).getResultList();
	}
}
