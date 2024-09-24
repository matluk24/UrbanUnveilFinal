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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Itinerario> add(@RequestBody User u, @RequestParam("title") String title) {
		Itinerario i= new Itinerario(u, title);
		return new ResponseEntity<Itinerario>(service.save(i), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Long id, @RequestBody String title) {
		Itinerario i=service.update(id, title);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(i, HttpStatus.OK);
	}
	
	@PutMapping("/addPoi")
	public ResponseEntity<?> addPoi(@RequestParam("id") Long id, @RequestBody POI p) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.addPoi(id, p), HttpStatus.OK);
	}
	
	@PutMapping("/addmorethanonePoi")
	public ResponseEntity<?> addListPoi(@RequestParam("id") Long id, @RequestBody List<POI> p) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.addPois(id, p), HttpStatus.OK);
	}
	
	@PutMapping("/removePoi")
	public ResponseEntity<String> removePoi(@RequestParam("id") Long id, @RequestBody POI p) {
		Itinerario i = service.removePoi(id, p);
		String s = "";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		if(i == null) {
			s = "L'itinerario non è stato trovato";
		}else if (i.isEmpty()) {
			s = "Errore nella rimozione del POI";
			status = HttpStatus.CONFLICT;
		}else {
			s = "POI rimosso con successo";
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<String>(s, status
				);
	}
	
	@PutMapping("/addContent")
	public ResponseEntity<?> addContent(@RequestParam("id") Long id, @RequestBody Content c) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.addContent(id, c), HttpStatus.OK);
	}
	
	@PutMapping("/addmorethanoneContent")
	public ResponseEntity<?> addMoreContent(@RequestParam("id") Long id, @RequestBody List<Content> c) {
		Itinerario i= service.get(id);
		if(i==null) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Itinerario>(service.addContents(id, c), HttpStatus.OK);
	}
	
	@PutMapping("/removeContent")
	public ResponseEntity<String> removeContent(@RequestParam("id") Long id, @RequestBody Content c) {
		Itinerario i = service.removeContent(id, c);
		String s = "";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		if(i == null) {
			s = "L'itinerario non è stato trovato";
		}else if (i.isEmpty()) {
			s = "Errore nella rimozione del Content";
			 status = HttpStatus.CONFLICT;
		}else {
			s = "Content rimosso con successo";
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<String>(s, status);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<?> remove(@RequestBody Long id) {
		if(!service.remove(id)) {
			return new ResponseEntity<String>("L'itinerario non è stato trovato", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("L'itinerario rimosso con successo", HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Itinerario i=service.get(id);
		if(i!=null) {
			return new ResponseEntity<Itinerario>(i, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("L'itinerario richiesto non è stato trovato", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Itinerario>> getAll() {
		return new ResponseEntity<List<Itinerario>>(service.getAll(), HttpStatus.OK);
		
	}
	
}
