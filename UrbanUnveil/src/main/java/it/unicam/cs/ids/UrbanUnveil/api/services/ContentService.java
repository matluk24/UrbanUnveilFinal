package it.unicam.cs.ids.UrbanUnveil.api.services;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;

public interface ContentService<T extends Content> {
	public Content save(T content);
	public Content load(Long id);
	public boolean delete(Long id);
	public Content update(Long id, String title, String desc, String path);
}
