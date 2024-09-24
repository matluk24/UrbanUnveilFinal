package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.RoleEnum;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.services.OSMService;
import it.unicam.cs.ids.UrbanUnveil.api.services.POIService;
import it.unicam.cs.ids.UrbanUnveil.api.services.UserService;

@RestController
@RequestMapping("/POI")
public class POIController {
	private POIService service;
	private OSMService osmS;
	private UserService userS;
	
	@Autowired
	public POIController(POIService u, UserService userS, OSMService osmS) {
		this.service = u;
		this.userS = userS;
		this.osmS = osmS;
	}
	
	public POIController() {
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody POI p) {
		if(p.getAutore().getRole().equals(RoleEnum.TRUSTEDCONTRIBUTOR)) {
			p.setStato(StateEnum.APPROVED);
		}
		else if(!p.getAutore().getRole().equals(RoleEnum.CONTRIBUTOR)) {
			p=null;
			return new ResponseEntity<String>("L'utente non è abilitato a creare un poi", HttpStatus.FORBIDDEN);
		}
		else {
			p.setStato(StateEnum.WAITING);
		}
		return new ResponseEntity<POI>(service.add(p), HttpStatus.OK);
		
	}
	
	@PostMapping("/addwithparam")
	public ResponseEntity<POI> add(@RequestParam("n") String n, @RequestBody List<Content> c, @RequestParam("uId") Long uId) {
		OSMNode node=null;
		try {
			node = osmS.search(n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(userS.get(uId).getRole().equals(RoleEnum.TRUSTEDCONTRIBUTOR)) {
			return new ResponseEntity<POI>(service.add(node, c, userS.get(uId), StateEnum.APPROVED), HttpStatus.OK);
		}
		return new ResponseEntity<POI>(service.add(node, c, userS.get(uId), null), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<POI>> getAll() {
			return new ResponseEntity<List<POI>>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getByID(@PathVariable("id") Long id) {
		if(service.getById(id)==null) {
			return new ResponseEntity<String>("Il poi richiesto non è stato trovato", HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<POI>(service.getById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> remove(@RequestBody Long id) {
		if(service.removeById(id)) {
			return new ResponseEntity<String>("Il Poi è stato eliminato correttamente", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Il Poi che si voleva eliminare non esiste", HttpStatus.NOT_FOUND);
		}
	}
}
