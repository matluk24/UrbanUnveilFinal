package it.unicam.cs.ids.UrbanUnveil.api.models;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.ContentEnum;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;

@Entity
public class TextContent extends Content {
    private String language;
    
    //Empty
    public TextContent() {
    	super();
    	language = null;
    }

    public TextContent(User publisher, StateEnum state, ContentEnum contentRef, String title, String descr, String path, String language) {
        super(publisher, state, contentRef, title, descr, path);
        this.language = language;
    }
}
