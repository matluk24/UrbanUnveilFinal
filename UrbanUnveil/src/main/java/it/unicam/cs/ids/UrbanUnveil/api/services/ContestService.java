package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Contest;

@Service
public interface ContestService {

	public Contest add(Contest c);
	public Contest get(Long id);
	public List<Contest> getAll();
	public boolean remove(Contest c);
	public Contest update(Contest c);
}
