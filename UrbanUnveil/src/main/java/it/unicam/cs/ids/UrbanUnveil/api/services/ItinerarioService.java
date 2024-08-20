package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Itinerario;

@Service
public interface ItinerarioService {

	public Itinerario save(Itinerario i);
	public Itinerario update(Long id, String t);
	public boolean remove(Long id);
	public Itinerario get(Long id);
	public List<Itinerario> getAll();
}
