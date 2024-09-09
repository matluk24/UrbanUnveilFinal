package it.unicam.cs.ids.UrbanUnveil.api.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.RoleEnum;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.services.POIService;

@RestController
@RequestMapping("/POI")
public class POIController {
	private POIService service;
	
	@Autowired
	public POIController(POIService u) {
		this.service = u;
	}
	
	public POIController() {
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<POI> add(@RequestBody POI p) {
		if(p.getAutore().getRole().equals(RoleEnum.TRUSTEDCONTRIBUTOR)) {
			p.setStato(StateEnum.APPROVED);
		}
		else if(!p.getAutore().getRole().equals(RoleEnum.CONTRIBUTOR)) {
			p=null;
			return new ResponseEntity<POI>(p, HttpStatus.FORBIDDEN);
		}
		else {
			p.setStato(StateEnum.WAITING);
		}
		return new ResponseEntity<POI>(service.add(p), HttpStatus.OK);
		
	}
	
	@PostMapping("/addwithparam")
	public ResponseEntity<POI> add(@RequestBody OSMNode n, @RequestBody List<Content> c, @RequestBody User u) {
		if(u.getRole().equals(RoleEnum.TRUSTEDCONTRIBUTOR)) {
			return new ResponseEntity<POI>(service.add(n, c, u, StateEnum.APPROVED), HttpStatus.OK);
		}
		return new ResponseEntity<POI>(service.add(n, c, u, null), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<POI>> getAll() {
			return new ResponseEntity<List<POI>>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<POI> getByID(@PathVariable("id") Long id) {
			return new ResponseEntity<POI>(service.getById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> remove(@RequestBody Long id) {
		if(service.removeById(id)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
	}
}
