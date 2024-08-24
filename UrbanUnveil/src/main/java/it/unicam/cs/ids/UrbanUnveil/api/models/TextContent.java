package it.unicam.cs.ids.UrbanUnveil.api.models;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;


@Entity
public class TextContent extends Content {
	
	private Long lineCount;
	private Long wordCount;
	private Long size;
	private static final String UPLOAD_DIR= "./Utils/Content/files/";
	
    //Empty
    public TextContent() {

    }

    public TextContent(User publisher, StateEnum state, String title, String descr, String fileName, Long size, Long lineCount, Long wordCount) {
        super(publisher, state, title, descr, UPLOAD_DIR + fileName);
        this.size = size;
        this.lineCount = lineCount;
        this.wordCount = wordCount;
    }
    public TextContent(Content content, String fileName, Long size, Long lineCount, Long wordCount) {
    	super(content, UPLOAD_DIR + fileName);
    	this.size = size;
        this.lineCount = lineCount;
        this.wordCount = wordCount;
    }

	public Long getLineCount() {
		return lineCount;
	}
	
	public Long getWordCount() {
		return wordCount;
	}
	public void setLineCount(Long lineCount) {
		this.lineCount = lineCount;
	}
	public void setWordCount(Long wordCount) {
		this.wordCount = wordCount;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}


}
