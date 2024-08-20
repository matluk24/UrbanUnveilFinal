package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Itinerario;
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
		}
		return repo.existsById(id);
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

}
