package com.example.Taller1QuinteroLuisa.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Taller1QuinteroLuisa.model.person.User;
import com.example.Taller1QuinteroLuisa.repository.UserRepository;

public class UserServiceImp implements UserService{
	private UserRepository userRepo;
	
	@Autowired
	public UserServiceImp(UserRepository userRepo) {
		this.userRepo= userRepo;
	}

	@Override
	public User save(User user, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user, long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
