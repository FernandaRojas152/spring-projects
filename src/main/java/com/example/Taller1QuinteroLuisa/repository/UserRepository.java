package com.example.Taller1QuinteroLuisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Taller1QuinteroLuisa.model.person.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
