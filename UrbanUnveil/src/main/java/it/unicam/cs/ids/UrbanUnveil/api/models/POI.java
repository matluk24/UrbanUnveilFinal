package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.Objects;

import it.unicam.cs.ids.UrbanUnveil.api.Enum.StateEnum;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class POI {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private OSMNode posizione;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Content> contenuti;
	@ManyToOne
	private User autore;
	private StateEnum stato;
	
	public POI() {
		
	}
	public POI (OSMNode n, User u, StateEnum s) {
		posizione=n;
		contenuti = new LinkedList<Content>();
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
	
	public void addContenuti(List<Content> contenuti) {
		this.contenuti.addAll(contenuti);
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
		boolean c= false;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POI other = (POI) obj;
		if(contenuti.isEmpty() && other.contenuti.isEmpty()) {
			c=true;
		}
		else if (!contenuti.equals(other.contenuti)) {
			c=false;
		}
		return autore.equals(other.autore) && c
				&& id==other.id && posizione.equals(other.posizione) && stato == other.stato;
	}

	@Override
	public String toString() {
		return "POI [id=" + id + ", posizione=" + posizione + ", contenuti=" + contenuti + ", autore=" + autore
				+ ", stato=" + stato + "]";
	}


	
}
