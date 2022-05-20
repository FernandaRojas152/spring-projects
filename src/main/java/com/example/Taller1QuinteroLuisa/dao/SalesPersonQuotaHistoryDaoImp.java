package com.example.Taller1QuinteroLuisa.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;

@Repository
@Scope("singleton")
public class SalesPersonQuotaHistoryDaoImp implements SalesPersonQuotaHistoryDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void save(Salespersonquotahistory salespersonquotahistory) {
		entityManager.persist(salespersonquotahistory);
	}
	
	@Transactional
	@Override
	public void update(Salespersonquotahistory salespersonquotahistory) {
		entityManager.merge(salespersonquotahistory);
	}
	
	@Transactional
	@Override
	public List<Salespersonquotahistory> findAll() {
		Query query= entityManager.createQuery("SELECT sp FROM Salespersonquotahistory sp");
		return query.getResultList();
	}

	@Transactional
	@Override
	public Salespersonquotahistory findById(Integer id) {
		return entityManager.find(Salespersonquotahistory.class, id);
	}

	@Transactional
	@Override
	public List<Salespersonquotahistory> findBySalesPerson(Integer salespersonid) {
		String jpql= "SELECT sp FROM Salespersonquotahistory sp WHERE sp.salesperson.businessentityid = '"+salespersonid+"'";
		return entityManager.createQuery(jpql, Salespersonquotahistory.class).getResultList();
	}

	@Override
	public List<Salespersonquotahistory> findBySalesQuota(BigDecimal salesquota) {
		String jpql= "SELECT sp FROM Salespersonquotahistory sp WHERE sp.salesquota = '"+salesquota+"'";
		return entityManager.createQuery(jpql, Salespersonquotahistory.class).getResultList();
	}
}
