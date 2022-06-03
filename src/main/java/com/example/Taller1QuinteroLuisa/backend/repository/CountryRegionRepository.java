package com.example.Taller1QuinteroLuisa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Taller1QuinteroLuisa.backend.model.person.Countryregion;

public interface CountryRegionRepository extends JpaRepository<Countryregion, String>{
	

}
