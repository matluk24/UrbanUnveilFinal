package it.unicam.cs.ids.UrbanUnveil.api.models;

import jakarta.persistence.*;

import java.util.Objects;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;

@Entity
public class Content {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User publisher; 
	private StateEnum state;
	private String title;
	private String descr;
	private String path;
	private String contentType;
	
	public Content() {
		
	}
	
	public Content (User pub, StateEnum s, String title, String descr, String path, String contentType) {
		this.publisher = pub;
		if(s==null) {
			state=StateEnum.WAITING;
		}
		else {
			state=s;
		}
		this.title = title;
		this.descr = descr;
		this.path = path;
		this.contentType = contentType;
	}
	
	public Content(Content content, String dest, String contentType) {
		this.publisher = content.getPublisher();
		if(content.getState()==null) {
			state=StateEnum.WAITING;
		}
		else {
			state=content.getState();
		}
		this.contentType=contentType;
		this.title = content.getTitle();
		this.descr = content.getDescr();
		this.path = dest;	
	}
	
	public Long getId() {
		return id;
	}

	public User getPublisher() {
		return publisher;
	}

	public StateEnum getState() {
		return state;
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
	public String getContenttype() {
		return contentType;
	}
	public void setContenttype(String contenttype) {
		this.contentType = contenttype;
	}
	public boolean isEmpty() {
		return (publisher == null) && (state == null) && (descr == null) && (title == null) && (path == null) && (contentType == null);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, descr, path, publisher, state, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		return id.equals(other.id) && descr.equals(other.descr) && path.equals(other.path)
				&& publisher.equals(other.publisher) && state == other.state
				&& title.equals(other.title);
	}

	@Override
	public String toString() {
		return "Content {id=" + id + ", descr=" + descr + ",publisher = "+publisher+ ", path = "+path+"}";
	}
	

}
