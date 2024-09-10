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

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.Itinerario;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.services.ItinerarioService;

@RestController
@RequestMapping("/itinerario")
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
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Long id, @RequestBody String t) {
		if(service.update(id, t)==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.update(id, t), HttpStatus.OK);
	}
	
	@PutMapping("/addPoi")
	public ResponseEntity<?> addPoi(@RequestBody Long id, @RequestBody POI p) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.addPoi(id, p), HttpStatus.OK);
	}
	
	@PutMapping("/addmorethanonePoi")
	public ResponseEntity<?> addListPoi(@RequestBody Long id, @RequestBody List<POI> p) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.addPois(id, p), HttpStatus.OK);
	}
	
	@PutMapping("/removePoi")
	public ResponseEntity<?> removePoi(@RequestBody Long id, @RequestBody POI p) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.removePoi(id, p), HttpStatus.OK);
	}
	
	@PutMapping("/addContent")
	public ResponseEntity<?> addContent(@RequestBody Long id, @RequestBody Content c) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.addContent(id, c), HttpStatus.OK);
	}
	
	@PutMapping("/addmorethanoneContent")
	public ResponseEntity<?> addMoreContent(@RequestBody Long id, @RequestBody List<Content> c) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.addContents(id, c), HttpStatus.OK);
	}
	
	@PutMapping("/removeContent")
	public ResponseEntity<?> addPoi(@RequestBody Long id, @RequestBody Content c) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.removeContent(id, c), HttpStatus.OK);
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
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Itinerario> get(@PathVariable("id") Long id) {
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
