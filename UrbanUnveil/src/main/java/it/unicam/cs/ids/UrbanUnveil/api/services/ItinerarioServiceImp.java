package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.Itinerario;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.repo.ItinerarioRepository;

@Service
public class ItinerarioServiceImp implements ItinerarioService {
	
	
private ItinerarioRepository repo;
	
	@Autowired
	public ItinerarioServiceImp(ItinerarioRepository r) {
			repo=r;
	}
	
	public ItinerarioServiceImp() {
		
	}

	@Override
	public Itinerario save(Itinerario i) {
		return repo.save(i);
	}
	
	@Override
	public Itinerario update(Long id, String t) {
		Itinerario i=null;;
		if(repo.existsById(id)) {
			i = this.get(id);
		}
		if(i==null) {
			return null;
		}
		i.setTitle(t);
		return repo.saveAndFlush(i);
	}

	@Override
	public boolean remove(Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Itinerario get(Long id) {
		if(repo.existsById(id)) {
			return repo.findById(id).get();
		}
		return null;
	}

	@Override
	public List<Itinerario> getAll() {
		return repo.findAll();
	}

	@Override
	public Itinerario addPoi(Long id, POI p) {
		Itinerario i = this.get(id);
		if(i==null) {
			return null;
		}
		i.addStop(p);
		return repo.saveAndFlush(i);
	}
	
	@Override
	public Itinerario addPois(Long id, List<POI> p) {
		Itinerario i = this.get(id);
		if(i==null) {
			return null;
		}
		i.addStops(p);
		return repo.saveAndFlush(i);
	}

	@Override
	public Itinerario removePoi(Long id, POI p) {
		Itinerario i = this.get(id);
		if(i==null) {
			return null;
		}
		i.removeStop(p);
		if(i.equals(repo.saveAndFlush(i))) {
			return i;
		}
		return null;
	}
	
	@Override
	public Itinerario addContent(Long id, Content c) {
		Itinerario i = this.get(id);
		if(i==null) {
			return null;
		}
		i.addContent(c);
		return repo.saveAndFlush(i);
	}
	
	public Itinerario addContents(Long id, List<Content> c) {
		Itinerario i = this.get(id);
		if(i==null) {
			return null;
		}
		i.addContents(c);
		return repo.saveAndFlush(i);
	}

	@Override
	public Itinerario removeContent(Long id, Content c) {
		Itinerario i = this.get(id);
		if(i==null) {
			return null;
		}
		i.removeContent(c);
		if(i.equals(repo.saveAndFlush(i))) {
			return i;
		}
		return null;
	}

}
