package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.services.Authentication;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private Authentication service;
	
	@Autowired
	public AuthenticationController(Authentication auth) {
		this.service = auth;
	}
	
	public AuthenticationController() {
		
	}
	
	@GetMapping("/Login")
	public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password){
		User u = service.checkValues(email, password);
		HttpStatus httpStatus = HttpStatus.OK;
		if (u.equals(null)) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<User>(u, httpStatus);
	}
	
	@PostMapping("/Sighin")
	public ResponseEntity<User> sighin(@RequestBody User user){
		//TODO fare la registrazione
		return null;
	}
}
