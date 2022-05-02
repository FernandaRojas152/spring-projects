package com.example.Taller1QuinteroLuisa.services;

import com.example.Taller1QuinteroLuisa.model.person.UserApp;

public interface UserService {
	public UserApp save(UserApp user);
	public UserApp update(UserApp user, long id);
}
