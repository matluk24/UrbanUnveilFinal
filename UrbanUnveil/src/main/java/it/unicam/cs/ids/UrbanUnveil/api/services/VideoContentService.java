package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.models.VideoContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.ContentRepository;

public class VideoContentService implements ContentService<VideoContent> {

	
	private ContentRepository repo;
	
	@Autowired
	public VideoContentService(ContentRepository r) {
			repo=r;
	}
	
	public VideoContentService() {
		
	}
	
	
	@Override
	public ResponseEntity<String> save(VideoContent content) throws IOException{
		if(content.equals(repo.save(content))){
			 return new ResponseEntity<String>("File saved, Successfully", HttpStatus.OK);
		 }
		 return new ResponseEntity<String>("Content result is null", HttpStatus.CONFLICT);
	}

	@Override
	public ResponseEntity<Content> load(Long id) throws IOException{
		if(repo.existsById(id)) {
			return new ResponseEntity<Content>(repo.findById(id).get(), HttpStatus.OK);
		}
		return new ResponseEntity<Content>(new TextContent(), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> delete(Long id) throws IOException{
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return new ResponseEntity<String>("Elemento eliminato con successo", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Elemento non trovato", HttpStatus.NOT_FOUND);

	}
	
	@Override
	public ResponseEntity<String> update(VideoContent c) throws IOException {
		 if(c.equals(repo.saveAndFlush(c))){
			 return new ResponseEntity<String>("File saved, Successfully", HttpStatus.OK);
		 }
		 return new ResponseEntity<String>("Content result is null", HttpStatus.CONFLICT);
		
	}

}
