package it.unicam.cs.ids.UrbanUnveil.api.models;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.ContentEnum;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;

@Entity
public class VideoContent extends Content {
    private int duration;
    
  //Empty
    public VideoContent() {
    	super();
    	duration = 0;
    }

    public VideoContent(User publisher, StateEnum state, ContentEnum contentRef, String title, String descr, String path, int duration) {
        super(publisher, state, contentRef, title, descr, path);
        this.duration = duration;
    }
}
