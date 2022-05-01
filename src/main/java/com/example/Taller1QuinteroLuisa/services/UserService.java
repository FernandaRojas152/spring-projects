package com.example.Taller1QuinteroLuisa.services;

import com.example.Taller1QuinteroLuisa.model.person.UserApp;
import com.example.Taller1QuinteroLuisa.model.person.UserType;

public interface UserService {
	public UserApp save(UserApp user, long id);
	public UserApp update(UserApp user, long id);
	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdmins();

	public Iterable<UserApp> findAllOperators();

	public UserType[] getTypes();
}
