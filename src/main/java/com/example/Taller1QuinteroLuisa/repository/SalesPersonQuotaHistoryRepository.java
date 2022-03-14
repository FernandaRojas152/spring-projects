package com.example.Taller1QuinteroLuisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.model.sales.SalespersonquotahistoryPK;

@Repository
public interface SalesPersonQuotaHistoryRepository extends JpaRepository<Salespersonquotahistory, SalespersonquotahistoryPK>{

}
