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
	public Content save(VideoContent content) throws IOException{
		if(content.equals(repo.save(content))){
			 return content;
		 }
		 return new VideoContent();
	}

	@Override
	public Content load(Long id) throws IOException{
		if(repo.existsById(id)) {
			return repo.findById(id).get();
		 }
		 return new VideoContent();
	}

	@Override
	public Content delete(Long id) throws IOException{
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return null;
		}
		return new VideoContent();

	}
	
	@Override
	public Content update(VideoContent c) throws IOException {
		if(c.equals(repo.saveAndFlush(c))){
			 return c;
		 }
		 return new VideoContent();
		
	}

}
