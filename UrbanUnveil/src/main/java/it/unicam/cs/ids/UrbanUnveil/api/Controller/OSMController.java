package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.json.JSONObject;

import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;
import it.unicam.cs.ids.UrbanUnveil.api.services.OSMService;

@RestController("/osm")
public class OSMController {

	@Autowired
	public OSMService service;
	
	public OSMController(OSMService o) {
		service=o;
	}
	
	public OSMController() {
		
	}
	
	@PostMapping("/search")
	public ResponseEntity<OSMNode> search(@RequestBody String query) throws IOException {
		
		OSMNode n = service.search(query);
		
		return new ResponseEntity<OSMNode>(n, HttpStatus.OK);
		
	}
	
}
