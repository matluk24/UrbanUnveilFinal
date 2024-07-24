package it.unicam.cs.ids.UrbanUnveil.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.repo.UserRepository;

@Service
public class AuthenticationService implements Authentication {
	
	private UserRepository userRepo;
	
	@Autowired
	public AuthenticationService(UserRepository userRepository) {
		this.userRepo=userRepository;
		
	}
	
	@Override
	public User checkValues (String email, String password) {
		User user = userRepo.findByEmail(email);
		if(user.equals(userRepo.findByPassword(password))) {
			return user;
		}	
		return null;
	}

	@Override
	public User registrazione(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
