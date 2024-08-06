package it.unicam.cs.ids.UrbanUnveil.api.services;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;

public interface ContentService<T extends Content> {
	public void save(T content);
	public Content load(Long id);
	public void delete(Long id);
}
