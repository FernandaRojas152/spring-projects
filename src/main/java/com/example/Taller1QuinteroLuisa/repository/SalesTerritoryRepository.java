package com.example.Taller1QuinteroLuisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;

@Repository
public interface SalesTerritoryRepository extends JpaRepository<Salesterritory, Integer>{

}
