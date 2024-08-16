package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.User;

@Service
public interface UserService {
	public User update (User u);
	public User add(User u);
	public boolean remove(User u);
	public List<User> getAll();
}
