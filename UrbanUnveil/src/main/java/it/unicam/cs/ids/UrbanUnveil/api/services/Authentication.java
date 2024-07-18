package it.unicam.cs.ids.UrbanUnveil.api.services;

import it.unicam.cs.ids.UrbanUnveil.api.models.User;

public interface Authentication {
	User checkValues(String email, String Password);
	User registrazione(User u);
}
