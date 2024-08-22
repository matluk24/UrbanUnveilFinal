package it.unicam.cs.ids.UrbanUnveil.api.models;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;

@Entity
public class VideoContent extends Content {
    private int duration;
    
  //Empty
    public VideoContent() {

    }

    public VideoContent(User publisher, StateEnum state, String title, String descr, String path, int duration) {
        super(publisher, state, title, descr, path);
        this.duration = duration;
    }
    
    public VideoContent(Content content) {
    	super(content);
    	duration = 0;
    }
    public int getDuration() {
		return duration;
	}
	public void setResolution(int duration) {
		this.duration = duration;
	}
}
