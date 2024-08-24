package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;

@Service
public interface ContentService<T extends Content> {
	public Content save(Content content) throws IOException;
	public Content load(Long id) throws IOException;
	public Content delete(Long id) throws IOException;
	public Content update(Content c) throws IOException;
}
