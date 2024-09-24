package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;

@Service
public interface ContentService {
	public Content save(Content content) throws IOException;
	public Content load(Long id) throws IOException;
	public Content delete(Long id) throws IOException;
	public Content update(Content c) throws IOException;
	public List<Content> getAll();
}
