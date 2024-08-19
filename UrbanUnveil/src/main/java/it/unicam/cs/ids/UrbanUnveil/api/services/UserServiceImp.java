package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.repo.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	private UserRepository userRepo;
	
	@Autowired
	public UserServiceImp(UserRepository userRepository) {
		this.userRepo=userRepository;
		
	}
	
	public UserServiceImp() {
		
	}
	
	@Override
	public User update(User u) {
		return userRepo.saveAndFlush(u);
	}

	@Override
	public User add(User u) {
		return userRepo.save(u);
	}

	@Override
	public boolean remove(User u) {
		userRepo.delete(u);
		return userRepo.existsById(u.getId());
	}
	
	@Override
	public User get(Long id) {
		if(userRepo.existsById(id)) {
			return userRepo.findById(id).get();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

}
