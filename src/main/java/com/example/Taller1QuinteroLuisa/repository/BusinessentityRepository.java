package com.example.Taller1QuinteroLuisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Taller1QuinteroLuisa.model.person.Businessentity;

@Repository
public interface BusinessentityRepository extends JpaRepository<Businessentity, Integer>{

}
