package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids.UrbanUnveil.api.models.Contest;
import it.unicam.cs.ids.UrbanUnveil.api.services.ContestService;

@RestController
@RequestMapping("/contest")
public class ContestController {

	@Autowired
	public ContestService service;
	
	public ContestController(ContestService o) {
		service=o;
	}
	
	public ContestController() {
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Contest> add(@RequestBody Contest c) {
		return new ResponseEntity<Contest>(service.save(c), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Contest> get(@PathVariable("id") Long id) {
		Contest c = service.load(id);
		if(c==null) {
			return new ResponseEntity<Contest>(c, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Contest>(c, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> delete(@RequestBody Contest c) {
		if(service.remove(c)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<Contest> update(@RequestBody Contest c) {
		return new ResponseEntity<Contest>(service.update(c), HttpStatus.OK);
	}
}
