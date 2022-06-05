package com.example.Taller1QuinteroLuisa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Taller1QuinteroLuisa.backend.model.hr.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
