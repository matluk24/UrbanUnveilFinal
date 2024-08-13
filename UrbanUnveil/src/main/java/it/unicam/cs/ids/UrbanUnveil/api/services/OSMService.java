package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;

@Service
public interface OSMService {

		
	public OSMNode search(String query) throws IOException;
		

}
