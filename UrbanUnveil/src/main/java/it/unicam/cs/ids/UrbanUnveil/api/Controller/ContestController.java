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
	public ResponseEntity<?> add(@RequestBody Contest c) {
		if(c.getPoi()!=null && c.getItin()!=null) {
			return new ResponseEntity<String>("Un contest non può essere associato sia ad un poi che ad un itinerario", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Contest>(service.add(c), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Contest c = service.get(id);
		if(c==null) {
			return new ResponseEntity<String>("Il contest cercato non è stato trovato", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Contest>(c, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Contest>> getAll() {
		return new ResponseEntity<List<Contest>>(service.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Contest c) {
		if(service.remove(c)) {
			return new ResponseEntity<String>("Il contest è stato rimosso con successo",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Il contest non è stato rimosso perchè non trovato",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Contest> update(@RequestBody Contest c) {
		return new ResponseEntity<Contest>(service.update(c), HttpStatus.OK);
	}
}
