package it.unicam.cs.ids.UrbanUnveil.api.services;

import org.springframework.beans.factory.annotation.Autowired;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.ImageContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.ContentRepository;

public class ImageContentService implements ContentService<ImageContent> {
	
	private ContentRepository repo;
	
	@Autowired
	public ImageContentService(ContentRepository r) {
			repo=r;
	}
	
	public ImageContentService() {
		
	}

	@Override
	public Content save(ImageContent content) {
		return repo.save(content);
	}

	@Override
	public Content load(Long id) {
		if(repo.existsById(id)) {
			return repo.findById(id).get();
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;

	}
	
	@Override
	public Content update(Long id, String t, String d, String p) {
		Content c = this.load(id);
		if(t!=null) {
			c.setTitle(t);
		}
		if(d!=null) {
			c.setDescr(t);
		}
		if(p!=null) {
			c.setPath(t);
		}
		
		return repo.saveAndFlush(c);
		
	}

}
