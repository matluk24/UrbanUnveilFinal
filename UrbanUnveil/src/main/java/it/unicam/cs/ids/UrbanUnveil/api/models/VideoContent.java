package it.unicam.cs.ids.UrbanUnveil.api.models;


import java.util.Objects;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;

@Entity
public class VideoContent extends Content {
    private String duration;
    private String frameRate;
    private String width;
    private String height;
    
    private static final String UPLOAD_DIR= System.getProperty("user.dir")+"/Utils/Content/video/";
    
  //Empty
    public VideoContent() {

    }

    public VideoContent(User publisher, StateEnum state, String title, String descr, String fileName, String duration,String contentType) {
        super(publisher, state, title, descr, UPLOAD_DIR + fileName, contentType);
        this.duration = duration;
    }
    
    public VideoContent(Content content, String fileName, String duration, String frameRate, String width, String height, String contentType) {
    	super(content, UPLOAD_DIR + fileName, contentType);
    	this.duration = duration;
    	this.frameRate = frameRate;
    	this.width = width;
    	this.height = height;
    	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(duration, frameRate, height, width);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoContent other = (VideoContent) obj;
		return Objects.equals(duration, other.duration)
				&& Objects.equals(frameRate, other.frameRate) && Objects.equals(height, other.height)
				&& Objects.equals(width, other.width);
	}

	@Override
	public String toString() {
		return "VideoContent [Duration=" + duration + ", frameRate=" + frameRate
				+ ", width=" + width + ", height=" + height + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
	}
	
	
}
