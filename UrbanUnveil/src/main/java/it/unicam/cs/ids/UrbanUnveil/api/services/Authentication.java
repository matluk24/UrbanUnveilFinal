package it.unicam.cs.ids.UrbanUnveil.api.services;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.User;

@Service
public interface Authentication {
	User checkValues(String email, String Password);
	User registrazione(User u);
}
