package it.unicam.cs.ids.UrbanUnveil.api.models;

import jakarta.persistence.*;

import java.util.Objects;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;

@Entity
public class Content {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@ManyToOne
	private User publisher;
	private StateEnum state;
	private String title;
	private String descr;
	private String path;
	private String contenttype;
	
	public Content() {
		
	}
	
	public Content (User pub, StateEnum s, String title, String descr, String path) {
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
	}
	
	public Content(Content content, String dest) {
		this.publisher = content.getPublisher();
		if(content.getState()==null) {
			state=StateEnum.WAITING;
		}
		else {
			state=content.getState();
		}
		this.contenttype=content.getContenttype();
		this.title = content.getTitle();
		this.descr = content.getDescr();
		this.path = dest;	
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
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
		System.out.println(this.contenttype);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, descr, path, publisher, state, title);
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
		return Id.equals(other.Id) && descr.equals(other.descr) && path.equals(other.path)
				&& publisher.equals(other.publisher) && state == other.state
				&& title.equals(other.title);
	}

	@Override
	public String toString() {
		return "Content {Id=" + Id + ", descr=" + descr + ",publisher = "+publisher+ ", path = "+path+"}";
	}
	

}
