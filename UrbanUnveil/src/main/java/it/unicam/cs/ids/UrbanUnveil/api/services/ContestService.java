package it.unicam.cs.ids.UrbanUnveil.api.services;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Contest;

@Service
public interface ContestService {

	public Contest save(Contest c);
	public Contest load(Long id);
	public boolean remove(Contest c);
	public Contest update(Contest c);
}
