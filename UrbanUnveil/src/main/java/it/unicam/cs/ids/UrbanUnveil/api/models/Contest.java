package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Contest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne
	private POI poi;
	@OneToOne
	private Itinerario itin;
	@OneToMany
	private List<Content> contents;
	
	public Contest() {
		
	}
	
	public Contest(String n, POI p, Itinerario i) {
		name=n;
		poi=p;
		itin=i;
		contents=new LinkedList<Content>();
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public POI getPoi() {
		return poi;
	}
	public void setPoi(POI poi) {
		this.poi = poi;
	}
	public Itinerario getItin() {
		return itin;
	}
	public void setItin(Itinerario itin) {
		this.itin = itin;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(contents, id, itin, name, poi);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contest other = (Contest) obj;
		return Objects.equals(contents, other.contents) && Objects.equals(id, other.id)
				&& Objects.equals(itin, other.itin) && Objects.equals(name, other.name)
				&& Objects.equals(poi, other.poi);
	}
	@Override
	public String toString() {
		return "Contest [id=" + id + ", name=" + name + ", poi=" + poi + ", itin=" + itin + ", contents=" + contents
				+ "]";
	}
	
	

}
