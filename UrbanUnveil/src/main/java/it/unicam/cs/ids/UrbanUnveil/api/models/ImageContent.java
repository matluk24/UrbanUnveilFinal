package it.unicam.cs.ids.UrbanUnveil.api.models;


import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;

@Entity
public class ImageContent extends Content {

	private String resolution;
	
	//Empty
	public ImageContent() {
		
	}

    public ImageContent(User publisher, StateEnum state, String title, String descr, String path, String resolution) {
        super(publisher, state, title, descr, path);
        this.resolution = resolution;
    }
    
	public ImageContent(Content content) {
		super(content);
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
}
