package it.unicam.cs.ids.UrbanUnveil.api.models;

import jakarta.persistence.*;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.ContentEnum;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;

@Entity
public abstract class Content {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@ManyToOne
	private User publisher;
	private StateEnum state;
	private ContentEnum content;
	private String title;
	private String descr;
	private String path;
	
	public Content() {
		
	}
	
	public Content (User pub, StateEnum state, ContentEnum contentRef, String title, String descr, String path) {
		this.publisher = pub;
		this.state = state;
		this.content = contentRef;
		this.title = title;
		this.descr = descr;
		this.path = path;	
	}
	
	public Long getId() {
		return Id;
	}

	public User getPublisher() {
		return publisher;
	}

	public StateEnum getState() {
		return state;
	}
	public ContentEnum getContentRef() {
		return content;
	}
	public void setState(StateEnum state) {
		this.state = state;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return "Content [Id=" + Id + ", state=" + content + ", descr=" + descr + "]";
	}
	
	//TODO IS Null method

}
