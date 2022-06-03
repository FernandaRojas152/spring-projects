package com.example.Taller1QuinteroLuisa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Taller1QuinteroLuisa.backend.model.person.UserApp;
import com.example.Taller1QuinteroLuisa.backend.model.person.UserType;

import java.util.List;

public interface UserRepository extends JpaRepository<UserApp, Long>{
	UserApp findByUsername(String username);
}
