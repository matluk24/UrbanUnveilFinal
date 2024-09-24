package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.Objects;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;
import jakarta.persistence.Entity;


@Entity
public class TextContent extends Content {
	
	private Long lineCount;
	private Long wordCount;
	private Long size;
	private static final String UPLOAD_DIR= System.getProperty("user.dir")+"/Utils/Content/text/plain/";
	
    //Empty
    public TextContent() {

    }

    public TextContent(User publisher, StateEnum state, String title, String descr, String fileName, Long size, Long lineCount, Long wordCount, String contentType) {
        super(publisher, state, title, descr, UPLOAD_DIR + fileName, contentType);
        this.size = size;
        this.lineCount = lineCount;
        this.wordCount = wordCount;
    }
    public TextContent(Content content, String fileName, Long size, Long lineCount, Long wordCount, String contentType) {
    	super(content, UPLOAD_DIR + fileName, contentType);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(lineCount, size, wordCount);
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
		TextContent other = (TextContent) obj;
		return Objects.equals(lineCount, other.lineCount) && Objects.equals(size, other.size)
				&& Objects.equals(wordCount, other.wordCount);
	}

	@Override
	public String toString() {
		return "TextContent [lineCount=" + lineCount + ", wordCount=" + wordCount + ", size=" + size +", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}

	

}
