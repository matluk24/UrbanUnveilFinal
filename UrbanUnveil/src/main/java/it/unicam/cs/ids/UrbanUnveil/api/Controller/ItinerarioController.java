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

import it.unicam.cs.ids.UrbanUnveil.api.models.Itinerario;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.services.ItinerarioService;

@RestController("/itinerario")
public class ItinerarioController {

	
	@Autowired
	public ItinerarioService service;
	
	public ItinerarioController(ItinerarioService o) {
		service=o;
	}
	
	public ItinerarioController() {
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Itinerario> add(@RequestBody User u, @RequestBody List<POI> p) {
		Itinerario i= new Itinerario(u);
		i.addStops(p);
		return new ResponseEntity<Itinerario>(service.save(i), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Itinerario> update(@RequestBody User u, @RequestBody List<POI> p) { //DA COMPLETARE
		Itinerario i= new Itinerario(u);
		i.addStops(p);
		return new ResponseEntity<Itinerario>(service.save(i), HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<HttpStatus> remove(@RequestBody Long id) {
		if(service.remove(id)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/get")
	public ResponseEntity<Itinerario> get(@RequestBody Long id) {
		Itinerario i;
		if(service.get(id)!=null) {
			i=service.get(id);
			return new ResponseEntity<Itinerario>(i, HttpStatus.OK);
		}
		else {
			i=null;
			return new ResponseEntity<Itinerario>(i, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Itinerario>> getAll() {
		return new ResponseEntity<List<Itinerario>>(service.getAll(), HttpStatus.OK);
		
	}
	
}
