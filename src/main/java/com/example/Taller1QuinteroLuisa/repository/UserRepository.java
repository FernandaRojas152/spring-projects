package com.example.Taller1QuinteroLuisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Taller1QuinteroLuisa.model.person.UserApp;
import com.example.Taller1QuinteroLuisa.model.person.UserType;

import java.util.List;


public interface UserRepository extends JpaRepository<UserApp, Long>{
	List<UserApp> findByUsername(String username);
	List<UserApp> findByType(UserType type);
}