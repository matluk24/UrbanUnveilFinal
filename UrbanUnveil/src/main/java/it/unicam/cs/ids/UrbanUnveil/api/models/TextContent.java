package it.unicam.cs.ids.UrbanUnveil.api.models;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;


@Entity
public class TextContent extends Content {
	
	private String encoding; 
	private Long lineCount;

	private Long wordCount;
    
	
    //Empty
    public TextContent() {

    }

    public TextContent(User publisher, StateEnum state, String title, String descr, String path, String endcoding, Long lineCount, Long wordCount) {
        super(publisher, state, title, descr, path);
        this.encoding = endcoding;
        this.lineCount = lineCount;
        this.wordCount = wordCount;
    }
    public TextContent(Content content, String dest) {
    	super(content, dest);
    	//TODO add param type
    }
    public String getEncoding() {
		return encoding;
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

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
