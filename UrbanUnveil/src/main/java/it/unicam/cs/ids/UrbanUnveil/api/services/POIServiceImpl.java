package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.OSMNode;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.repo.POIRepository;

public class POIServiceImpl implements POIService {
	
private POIRepository poiRepo;
	
	@Autowired
	public POIServiceImpl(POIRepository poiRepo) {
		this.poiRepo=poiRepo;
		
	}

	@Override
	public POI addPOI(POI p) {
		return poiRepo.save(p);
	}
	

	@Override
	public POI addPOI(OSMNode n, List<Content> c, User u, StateEnum s) {
		if (n==null || c==null) {
			throw new NullPointerException();
		}
		POI p = new POI(n, c, u, s);
		return poiRepo.save(p);
	}

	@Override
	public boolean removePOIById(Long id) {
		if (poiRepo.existsById(id)) {
			poiRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<POI> getAllPOI() {
		return poiRepo.findAll();
	}

	@Override
	public POI getPOIById(Long id) {
		if(poiRepo.existsById(id)) {
			return poiRepo.findById(id).get();
		}
		return null;
	}

}
