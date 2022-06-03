package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.person.UserApp;

public interface UserService {
	public UserApp save(UserApp user);
	public UserApp update(UserApp user, long id);
}
