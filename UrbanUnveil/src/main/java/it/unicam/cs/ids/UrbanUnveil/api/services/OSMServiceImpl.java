package it.unicam.cs.ids.UrbanUnveil.api.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;

@Service
public class OSMServiceImpl implements OSMService {

	public OSMServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public OSMNode search(String query) {
		RestTemplate r = new RestTemplate();
		String url =String.format("%s?q=%s&format=json", "https://nominatim.openstreetmap.org/search", query);
		try{
			ResponseEntity<String> responce = r.getForEntity(url, String.class);
			JSONArray json = new JSONArray(responce.getBody());
			JSONObject o =json.getJSONObject(0);
			
			return new OSMNode(o.getLong("place_id"), o.getString("name"), o.getDouble("lat"), o.getDouble("lon"));
			
		}catch(RestClientException ex) {
			ex.printStackTrace();
		}
		return null;
	}


}
