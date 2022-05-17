package com.example.Taller1QuinteroLuisa.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritoryhistory;

@Repository
@Scope("Singleton")
public class SalesTerritoryHistoryDaoImp implements SalesTerritoryHistoryDAO{
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Override
	public void save(Salesterritoryhistory salesterritoryhistory) {
		entityManager.persist(salesterritoryhistory);
	}
	
	@Transactional
	@Override
	public void update(Salesterritoryhistory salesterritoryhistory) {
		entityManager.merge(salesterritoryhistory);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Salesterritoryhistory> findAll() {
		Query query= entityManager.createQuery("SELECT sth FROM Salesterritoryhistory sth");
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public Salesterritoryhistory findById(Integer id) {
		return entityManager.find(Salesterritoryhistory.class, id);
	}
	
	@Transactional
	@Override
	public List<Salesterritoryhistory> findBySalesPerson(Integer salespersonid) {
		String jpql= "SELECT sth FROM Salesterritoryhistory sth WHERE sth.salesperson.businessentityid = '"+salespersonid+"'";
		return entityManager.createQuery(jpql, Salesterritoryhistory.class).getResultList();
	}
	
	@Transactional
	@Override
	public List<Salesterritoryhistory> findBySalesTerritory(Integer salesterritoryid) {
		String jpql= "SELECT s FROM Salesperson s WHERE s.salesterritory.territoryid = '"+salesterritoryid+"'";
		return entityManager.createQuery(jpql, Salesterritoryhistory.class).getResultList();
	}
}
