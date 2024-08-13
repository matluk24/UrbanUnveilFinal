package it.unicam.cs.ids.UrbanUnveil.api.models;

import org.springframework.web.multipart.MultipartFile;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.ContentEnum;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;

@Entity
public class ImageContent extends Content {

	private String resolution;
	
	//Empty
	public ImageContent() {
		super();
		resolution = null;
	}

    public ImageContent(User publisher, StateEnum state, ContentEnum contentRef, String title, String descr, String path, String resolution) {
        super(publisher, state, contentRef, title, descr, path);
        this.resolution = resolution;
    }
    

}
