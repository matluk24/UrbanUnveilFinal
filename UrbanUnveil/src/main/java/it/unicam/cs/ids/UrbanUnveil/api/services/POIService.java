package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import it.unicam.cs.ids.UrbanUnveil.api.models.POI;

public interface POIService {

	public POI addPOI(POI p);
	public POI addPOI(OSMNode n, List<Contenuti> c);
	public boolean removePOIById(Long id);
	public List<POI> getAllPOI();
	public POI getPOIById(Long id);
	
}
