package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.VideoContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.VideoContentRepository;

@Service
@Qualifier("videoContentService")
public class VideoContentService implements ContentService {


	@Autowired
	private VideoContentRepository repo;
	
	
	public VideoContentService() {
		
	}
	
	
	@Override
	public Content save(Content content) throws IOException{
		if(content instanceof VideoContent) {
			VideoContent videoContent = (VideoContent) content;
			if(videoContent.equals(repo.save(videoContent))){
				return videoContent;
			}
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
			return  new VideoContent();
		}
		return null;

	}
	
	@Override
	public Content update(Content content) throws IOException {
		if(content instanceof VideoContent) {
			VideoContent videoContent = (VideoContent) content;
			if(videoContent.equals(repo.saveAndFlush(videoContent))){
				return videoContent;
			}
		}
		 return new VideoContent();
		
	}
	public List<Content> getAll(){
		return new LinkedList<Content>(repo.findAll());
	}

}
