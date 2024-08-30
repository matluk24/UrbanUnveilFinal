package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.ImageContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.ContentRepository;

@Service
public class ImageContentService implements ContentService<ImageContent> {
	
	private ContentRepository repo;
	
	@Autowired
	public ImageContentService(ContentRepository r) {
			repo=r;
	}
	
	public ImageContentService() {
		
	}

	@Override
	public Content save(ImageContent content) throws IOException{
		
		if(content.equals(repo.save(content))){
			 return content;
		 }
		 return new ImageContent();
	}	 
	
	@Override
	public Content load(Long id) throws IOException{
		if(repo.existsById(id)) {
			return repo.findById(id).get();
		 }
		return new ImageContent();
	}

	@Override
	public Content delete(Long id) throws IOException{
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return null;
		}
		return new ImageContent();

	}
	
	@Override
	public Content update(ImageContent c) throws IOException {
		 if(c.equals(repo.saveAndFlush(c))){
			 return c;
		 }
		 return new ImageContent();
		
	}

}
