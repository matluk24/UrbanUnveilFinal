package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.Itinerario;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;

@Service
public interface ItinerarioService {

	public Itinerario save(Itinerario i);
	public Itinerario update(Long id, String t);
	public Itinerario addPoi(Long id, POI p);
	public Itinerario addPois(Long id, List<POI> p);
	public Itinerario removePoi(Long id, POI p);
	public Itinerario addContent(Long id, Content c);
	public Itinerario addContents(Long id, List<Content> c);
	public Itinerario removeContent(Long id, Content c);
	public boolean remove(Long id);
	public Itinerario get(Long id);
	public List<Itinerario> getAll();
}
