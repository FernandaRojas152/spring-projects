package com.example.Taller1QuinteroLuisa.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.model.person.UserApp;
import com.example.Taller1QuinteroLuisa.model.person.UserType;
import com.example.Taller1QuinteroLuisa.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	private UserRepository userRepo;
	
	@Autowired
	public UserServiceImp(UserRepository userRepo) {
		this.userRepo= userRepo;
	}

	@Override
	public UserApp save(UserApp user) {
		return userRepo.save(user);
	}

	@Override
	public UserApp update(UserApp user, long id) {
		UserApp aux= null;
		Optional<UserApp> temp = this.userRepo.findById(id);
		if(temp.isPresent()) {
			aux= save(user);
		}
		return aux;
	}

	@Override
	public Iterable<UserApp> findAll() {
		return userRepo.findAll();
	}

	@Override
	public Iterable<UserApp> findAllAdmins() {
		return userRepo.findByType(UserType.ADMINISTRATOR);
	}

	@Override
	public Iterable<UserApp> findAllOperators() {
		return userRepo.findByType(UserType.OPERATOR);
	}

	@Override
	public UserType[] getTypes() {
		return UserType.values();
	}
}
