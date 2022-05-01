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
	public UserApp save(UserApp user, long id) {
		return userRepo.save(user);
	}

	@Override
	public UserApp update(UserApp user, long id) {
		UserApp aux= null;
		Optional<UserApp> temp = this.userRepo.findById(id);
		
		if(temp.isPresent()) {
			aux= save(user, id);
		}	
		return aux;
	}

	@Override
	public Iterable<UserApp> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<UserApp> findAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<UserApp> findAllOperators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
