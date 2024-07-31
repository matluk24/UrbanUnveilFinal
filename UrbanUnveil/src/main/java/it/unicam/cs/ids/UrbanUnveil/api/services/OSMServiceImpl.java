package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.IOException;

import org.h2.util.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;

public class OSMServiceImpl implements OSMService {

	public OSMServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public OSMNode search(String query) {
		RestTemplate r = new RestTemplate();
		String url =String.format("%s?q=%s&format=json", "https://nominatim.openstreetmap.org/search", query);
		try{
			ResponseEntity<String> responce = r.getForEntity(url, String.class);
			JSONArray json = new JSONArray(responce.getBody());		//Gestisci json
			json.
			
		}catch(RestClientException ex) {
			ex.printStackTrace();
		}
		return null;
	}


}
