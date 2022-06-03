package com.example.Taller1QuinteroLuisa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;

@Repository
public interface SalesTerritoryRepository extends JpaRepository<Salesterritory, Integer>{

}
