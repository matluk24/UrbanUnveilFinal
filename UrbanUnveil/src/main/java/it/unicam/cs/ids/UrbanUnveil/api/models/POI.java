package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.Objects;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;

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
	private User autore;
	private StateEnum stato;
	
	public POI (OSMNode n, List<Content> c, User u, StateEnum s) {
		posizione=n;
		contenuti=c;
		autore=u;
		if(s==null) {
			stato=StateEnum.WAITING;
		}
		else {
			stato=s;
		}
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

	public User getAutore() {
		return autore;
	}

	public void setAutore(User autore) {
		this.autore = autore;
	}

	public StateEnum getStato() {
		return stato;
	}

	public void setStato(StateEnum stato) {
		this.stato = stato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autore, contenuti, id, posizione, stato);
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
		return Objects.equals(autore, other.autore) && Objects.equals(contenuti, other.contenuti)
				&& Objects.equals(id, other.id) && Objects.equals(posizione, other.posizione) && stato == other.stato;
	}

	@Override
	public String toString() {
		return "POI [id=" + id + ", posizione=" + posizione + ", contenuti=" + contenuti + ", autore=" + autore
				+ ", stato=" + stato + "]";
	}
	
	
}
