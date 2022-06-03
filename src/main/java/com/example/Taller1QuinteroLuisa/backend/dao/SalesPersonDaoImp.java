package com.example.Taller1QuinteroLuisa.backend.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;

@Repository
@Scope("singleton")
public class SalesPersonDaoImp implements SalesPersonDAO{
	
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void save(Salesperson salesperson) {
		entityManager.persist(salesperson);
		
	}

	@Override
	@Transactional
	public void update(Salesperson salesperson) {
		entityManager.merge(salesperson);
		
	}

	@Override
	@Transactional
	public List<Salesperson> findAll() {
		Query query= entityManager.createQuery("SELECT s FROM Salesperson s");
		return query.getResultList();
	}

	@Override
	@Transactional
	public Salesperson findById(Integer id){
		return entityManager.find(Salesperson.class, id);
	}

	@Override
	@Transactional
	public List<Salesperson> findBySalesTerritory(Integer territoryid) {
		String jpql= "SELECT s FROM Salesperson s WHERE s.salesterritory.territoryid = '"+territoryid+"'";
		return entityManager.createQuery(jpql, Salesperson.class).getResultList();
	}

	@Override
	public List<Salesperson> findByCommision(BigDecimal commissionpct){
		String jpql= "SELECT s FROM Salesperson s WHERE s.commissionpct = '"+commissionpct+"'";
		return entityManager.createQuery(jpql, Salesperson.class).getResultList();
	}

	@Override
	public List<Salesperson> findBySalesQuota(BigDecimal salesquota){
		String jpql= "SELECT s FROM Salesperson s WHERE s.salesquota = '"+salesquota+"'";
		return entityManager.createQuery(jpql, Salesperson.class).getResultList();
	}

	@Override
	public List<Object[]> queryPerson(Salesterritory salesterritory, Date start, Date end) {
		String jpql="SELECT s FROM Salesperson s WHERE (SELECT COUNT(sth) FROM Salesterritoryhistory sth"
				+"WHERE sth MEMBER OF s.salesterritoryhistories AND sth.startdate>= start"+
				" AND sth.enddate<= end)>0 AND st= s.salesterritory.territoryid";

		Query query= entityManager.createQuery(jpql);
		query.setParameter("st", salesterritory.getTerritoryid());
		query.setParameter("start", start);
		query.setParameter("end", end);
		
		return entityManager.createQuery(jpql, Object[].class).getResultList();
	}
}
