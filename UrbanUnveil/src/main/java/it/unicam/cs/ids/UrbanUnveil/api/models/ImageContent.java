package it.unicam.cs.ids.UrbanUnveil.api.models;


import java.util.Objects;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;

@Entity
public class ImageContent extends Content{

	private int width;
	private int height;
	private String format;
	
	private static final String UPLOAD_DIR= System.getProperty("user.dir")+"/Utils/Content/image/";
	
	//Empty
	public ImageContent() {
		
	}

    public ImageContent(User publisher, StateEnum state, String title, String descr, String fileName, int width, int height,String format) {
        super(publisher, state, title, descr, UPLOAD_DIR + fileName);
        this.width = width;
        this.height = height;
        this.format = format;
    }
    
	public ImageContent(Content content, String fileName, int width, int height,String format) {
		super(content, UPLOAD_DIR + fileName);
		this.width = width;
        this.height = height;
        this.format = format;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(format, height, width);
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
		ImageContent other = (ImageContent) obj;
		return Objects.equals(format, other.format) && height == other.height && width == other.width;
	}

	@Override
	public String toString() {
		return "ImageContent [width=" + width + ", height=" + height + ", format=" + format + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}
	
	

}
