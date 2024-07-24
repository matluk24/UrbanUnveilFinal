package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService service;
	
	@Autowired
	public UserController(UserService u) {
		this.service = u;
	}
	
	public UserController() {
		
	}
	
	@PostMapping("/update")
	public ResponseEntity<User> update(@RequestBody User u){
		return new ResponseEntity<User>(service.update(u), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> add(@RequestBody User u){
		return new ResponseEntity<User>(service.add(u), HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<HttpStatus> remove(@RequestBody User u){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		if(service.remove(u)) {
			httpStatus =   HttpStatus.OK;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<List<User>>(service.getAll(), HttpStatus.OK);
	}
}
