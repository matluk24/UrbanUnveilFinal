package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Contest;
import it.unicam.cs.ids.UrbanUnveil.api.repo.ContestRepository;

@Service
public class ContestServiceImpl implements ContestService {

	
private ContestRepository repo;
	
	@Autowired
	public ContestServiceImpl(ContestRepository r) {
			repo=r;
	}
	
	public ContestServiceImpl() {
	
	}
		
	
	@Override
	public Contest add(Contest c) {
		return repo.save(c);
	}

	@Override
	public Contest get(Long id) {
		if(repo.existsById(id)) {
			return repo.findById(id).get();
		}
		return null;
	}
	
	@Override
	public List<Contest> getAll() {
		return repo.findAll();
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
	public Contest update(Contest c) {
		Contest a = repo.findById(c.getId()).get();
		a.setName(c.getName());
		a.setPoi(c.getPoi());
		a.setItin(c.getItin());
		a.addContents(c.getContents());
		return repo.saveAndFlush(a);
	}

}
