package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Itinerario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@ManyToOne
	private User publisher;
	private List<POI> stops;
	private List<Content> contents;
	
	public Itinerario(User u) {
		publisher=u;
		
	}
	
	public User getPublisher() {
		return publisher;
	}
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	public List<POI> getStops() {
		return stops;
	}
	public void setStops(List<POI> stops) {
		this.stops = stops;
	}
	public List<Content> getContents() {
		return contents;
	}
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Id, contents, publisher, stops);
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
		return Objects.equals(Id, other.Id) && Objects.equals(contents, other.contents)
				&& Objects.equals(publisher, other.publisher) && Objects.equals(stops, other.stops);
	}
	@Override
	public String toString() {
		return "Itinerario [Id=" + Id + ", publisher=" + publisher + ", stops=" + stops + ", contents=" + contents
				+ "]";
	}
	
	
	
}
