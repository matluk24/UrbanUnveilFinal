package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.Objects;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class POI {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private OSMNode posizione;
	private List<Content> contenuti;
	// private non mi viene in mente altro
	
	public POI (OSMNode n, List<Contenuti> c) {
		posizione=n;
		contenuti=c;
	}
	
	public OSMNode getPosizione() {
		return posizione;
	}
	public void setPosizione(OSMNode posizione) {
		this.posizione = posizione;
	}
	public List<Content> getContenuti() {
		return contenuti;
	}
	public void setContenuti(List<Content> contenuti) {
		this.contenuti = contenuti;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POI other = (POI) obj;
		return id == other.id;
	}
	
	
}
