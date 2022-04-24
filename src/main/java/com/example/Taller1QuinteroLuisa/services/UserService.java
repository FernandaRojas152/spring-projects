package com.example.Taller1QuinteroLuisa.services;

import com.example.Taller1QuinteroLuisa.model.person.User;

public interface UserService {
	public User save(User user, long id);
	public User update(User user, long id);
}
