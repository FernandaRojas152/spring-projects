package com.example.Taller1QuinteroLuisa.backend.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;

@Repository
@Scope("singleton")
public class SalesTerritoryDaoImp implements SalesTerritoryDAO{

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void save(Salesterritory salesterritory) {
		entityManager.persist(salesterritory);
	}

	@Transactional
	@Override
	public void update(Salesterritory salesterritory) {
		entityManager.merge(salesterritory);
	}
	
	@Transactional
	@Override
	@SuppressWarnings("unchecked")
	public List<Salesterritory> findAll() {
		Query query = entityManager.createQuery("SELECT st FROM Salesterritory st");
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public Salesterritory findById(Integer id) {
		return entityManager.find(Salesterritory.class, id);
	}

	@Override
	public List<Salesterritory> findTwoSalesPersonWithQuota() {
		String jpql= "SELECT st FROM Salesterritory st WHERE (SELECT COUNT(s) FROM Salesperson s WHERE s MEMBER OF st.salespersons AND (s.salesquota)>1000)>=2";
		return entityManager.createQuery(jpql).getResultList();
	}

}
