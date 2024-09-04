package it.unicam.cs.ids.UrbanUnveil.api.models;


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

}
