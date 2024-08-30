	package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;

@Service
public interface POIService {

	public POI add(POI p);
	public POI add(OSMNode n, List<Content> c, User u, StateEnum s);
	public boolean removeById(Long id);
	public List<POI> getAll();
	public POI getById(Long id);
	
}
