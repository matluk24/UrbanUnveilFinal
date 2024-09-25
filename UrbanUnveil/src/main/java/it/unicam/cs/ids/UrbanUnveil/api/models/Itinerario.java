package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Itinerario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	@ManyToOne
	private User publisher;
	@OneToMany(cascade=CascadeType.ALL)
	private List<POI> stops;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Content> contents;
	
	public Itinerario(User u, String t) {
		title=t;
		publisher=u;
		stops=new LinkedList<POI>();
		contents = new LinkedList<Content>();
	}
	
	public Itinerario() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public User getPublisher() {
		return publisher;
	}
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<POI> getStops() {
		return stops;
	}
	public void setStops(List<POI> stops) {
		this.stops = stops;
	}
	public void addStops(List<POI> stops) {
		this.stops.addAll(stops);
	}
	public void addStop(POI stop) {
		this.stops.add(stop);
	}
	public void removeStop(POI stop) {
		this.stops.remove(stop);
	}
	public List<Content> getContents() {
		return contents;
	}
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	public void addContents(List<Content> contents) {
		this.contents.addAll(contents);
	}
	public void addContent(Content content) {
		this.contents.add(content);
	}
	public void removeContent(Content content) {
		this.contents.remove(content);
	}
	
	public boolean isEmpty() {
		return (stops == null)  &&  (contents == null );
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, contents, publisher, stops);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerario other = (Itinerario) obj;
		return Objects.equals(id, other.id) && Objects.equals(contents, other.contents)
				&& Objects.equals(publisher, other.publisher) && Objects.equals(stops, other.stops);
	}
	@Override
	public String toString() {
		return "Itinerario [Id=" + id + ", publisher=" + publisher + ", stops=" + stops + ", contents=" + contents
				+ "]";
	}
	
	
	
}
