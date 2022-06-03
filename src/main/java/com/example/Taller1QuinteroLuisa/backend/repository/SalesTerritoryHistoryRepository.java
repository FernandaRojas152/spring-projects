package com.example.Taller1QuinteroLuisa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.SalesterritoryhistoryPK;

public interface SalesTerritoryHistoryRepository extends JpaRepository<Salesterritoryhistory, Integer>{

}
