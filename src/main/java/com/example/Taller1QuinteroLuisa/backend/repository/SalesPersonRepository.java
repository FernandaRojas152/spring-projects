package com.example.Taller1QuinteroLuisa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;

@Repository
public interface SalesPersonRepository extends JpaRepository<Salesperson, Integer>{

}
