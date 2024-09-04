package it.unicam.cs.ids.UrbanUnveil.api.models;


import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;

@Entity
public class VideoContent extends Content {
	private String contentType;
    private String duration;
    private String frameRate;
    private String width;
    private String height;
    
    private static final String UPLOAD_DIR= System.getProperty("user.dir")+"/Utils/Content/video/";
    
  //Empty
    public VideoContent() {

    }

    public VideoContent(User publisher, StateEnum state, String title, String descr, String fileName, String duration) {
        super(publisher, state, title, descr, UPLOAD_DIR + fileName);
        this.duration = duration;
    }
    
    public VideoContent(Content content, String fileName, String contentType, String duration, String frameRate, String width, String height) {
    	super(content, UPLOAD_DIR + fileName);
    	this.contentType = contentType;
    	this.duration = duration;
    	this.frameRate = frameRate;
    	this.width = width;
    	this.height = height;
    	
    }
    public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFrameRate() {
		return frameRate;
	}

	public void setFrameRate(String frameRate) {
		this.frameRate = frameRate;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}
	public void setResolution(String duration) {
		this.duration = duration;
	}
}
