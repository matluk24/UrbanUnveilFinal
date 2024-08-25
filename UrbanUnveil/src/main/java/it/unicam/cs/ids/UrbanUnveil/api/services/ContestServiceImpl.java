package it.unicam.cs.ids.UrbanUnveil.api.services;

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
	public Contest save(Contest c) {
		return repo.save(c);
	}

	@Override
	public Contest load(Long id) {
		if(repo.existsById(id)) {
			return repo.findById(id).get();
		}
		return null;
	}

	@Override
	public boolean remove(Contest c) {
		if(repo.existsById(c.getId())) {
			repo.deleteById(c.getId());
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
