package it.unicam.cs.ids.UrbanUnveil.api.models;

import org.springframework.web.multipart.MultipartFile;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.ContentEnum;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class TextContent extends Content {
    private String language;
    private MultipartFile file;
    
    //Empty
    public TextContent() {
    	super();
    	language = null;
        file= null;
    }

    public TextContent(User publisher, StateEnum state, ContentEnum contentRef, String title, String descr, String path, String language, MultipartFile file) {
        super(publisher, state, contentRef, title, descr, path);
        this.language = language;
        this.file = file;
    }
    public MultipartFile getFile() {
    	return file;
    }
}
