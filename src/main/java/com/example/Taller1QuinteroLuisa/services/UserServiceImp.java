package com.example.Taller1QuinteroLuisa.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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
	@Transactional
	public UserApp save(UserApp user) {
		return userRepo.save(user);
	}

	@Override
	@Transactional
	public UserApp update(UserApp user, long id) {
		UserApp aux= null;
		Optional<UserApp> temp = this.userRepo.findById(id);
		if(temp.isPresent()) {
			aux= save(user);
		}
		return aux;
	}
	
	public Optional<UserApp> findById(long id) {
		return userRepo.findById(id);
	}

	public Iterable<UserApp> findAll() {
		return userRepo.findAll();
	}

	public Iterable<UserApp> findAllAdmins() {
		return userRepo.findByType(UserType.administrator);
	}

	public Iterable<UserApp> findAllOperators() {
		return userRepo.findByType(UserType.operator);
	}

	public UserType[] getTypes() {
		return UserType.values();
	}
}
