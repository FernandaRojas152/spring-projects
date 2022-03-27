package com.example.Taller1QuinteroLuisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.model.sales.SalesterritoryhistoryPK;

public interface SalesTerritoryHistoryRepository extends JpaRepository<Salesterritoryhistory, Integer>{

}
