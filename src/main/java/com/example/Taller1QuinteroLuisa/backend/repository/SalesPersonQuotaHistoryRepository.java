package com.example.Taller1QuinteroLuisa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.SalespersonquotahistoryPK;

@Repository
public interface SalesPersonQuotaHistoryRepository extends JpaRepository<Salespersonquotahistory, Integer>{

}
