package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;

public interface POIService {

	public POI addPOI(POI p);
	public POI addPOI(OSMNode n, List<Content> c, User u, StateEnum s);
	public boolean removePOIById(Long id);
	public List<POI> getAllPOI();
	public POI getPOIById(Long id);
	
}
