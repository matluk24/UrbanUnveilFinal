package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PutMapping("/update")
	public ResponseEntity<User> update(@RequestBody User u){
		return new ResponseEntity<User>(service.update(u), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> add(@RequestBody User u){
		return new ResponseEntity<User>(service.add(u), HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<String> remove(@RequestBody Long id){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		String s="L'user inserito non è corretto o non esiste";
		if(service.remove(id)) {
			httpStatus =   HttpStatus.OK;
			s="L'user inserito è stato eliminato correttamente";
		}
		return new ResponseEntity<String>(s,httpStatus);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		User u = service.get(id);
		if(u==null) {
			return new ResponseEntity<String>("L'utente richiesto non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<List<User>>(service.getAll(), HttpStatus.OK);
	}
}
