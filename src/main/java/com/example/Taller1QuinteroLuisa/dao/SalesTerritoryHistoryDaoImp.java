package com.example.Taller1QuinteroLuisa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("Singleton")
public class SalesTerritoryHistoryDaoImp{
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

}
