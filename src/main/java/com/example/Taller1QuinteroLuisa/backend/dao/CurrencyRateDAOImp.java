package com.example.Taller1QuinteroLuisa.backend.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;

@Repository
@Scope("singleton")
public class CurrencyRateDAOImp implements CurrencyRateDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
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
	public Currencyrate findById(Integer id) {
		return entityManager.find(Currencyrate.class, id);
	}

	@Override
	public void deleteAll() {
		Query query= entityManager.createQuery("DELETE FROM Currencyrate");
		query.executeUpdate();
	}

	@Override
	public List<Currencyrate> findByCurrency(String currencycode) {
		String jpql= "SELECT c FROM Currencyrate c WHERE c.currency1.currencycode = '"+currencycode+"'";
		return entityManager.createQuery(jpql, Currencyrate.class).getResultList();
	}
}
