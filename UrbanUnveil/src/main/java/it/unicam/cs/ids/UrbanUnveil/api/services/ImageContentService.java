package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.ImageContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.ImageContentRepository;

@Service
@Qualifier("imageContentService")
public class ImageContentService implements ContentService {
	
	@Autowired
	private ImageContentRepository repo;
	
	
	public ImageContentService() {
		
	}

	@Override
	public Content save(Content content) throws IOException{
		if(content instanceof ImageContent) {
			ImageContent imageContent = (ImageContent) content;
			if(imageContent.equals(repo.save(imageContent))){
				return imageContent;
			}
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
	public Content update(Content content) throws IOException {
		if(content instanceof ImageContent) {
			ImageContent imageContent = (ImageContent) content;
			if(imageContent.equals(repo.saveAndFlush(imageContent))){
				return imageContent;
			}
		}
		 return new ImageContent();
		
	}

}
