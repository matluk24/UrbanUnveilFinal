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
import it.unicam.cs.ids.UrbanUnveil.api.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private Authentication service;
	private UserService uService;
	
	@Autowired
	public AuthenticationController(Authentication auth, UserService u) {
		this.service = auth;
		uService=u;
	}
	
	public AuthenticationController() {
		
	}
	
	@PostMapping("/Login")
	public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password){
		User u = service.checkValues(email, password);
		HttpStatus httpStatus = HttpStatus.OK;
		if (u.equals(null)) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<User>(u, httpStatus);
	}
	
	@PostMapping("/Signin")
	public ResponseEntity<?> sighin(@RequestBody User user){
		if(uService.get(user.getId())!=null) {
			return new ResponseEntity<String>("L'utente esiste già", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(service.registrazione(user), HttpStatus.OK);
	}
}
