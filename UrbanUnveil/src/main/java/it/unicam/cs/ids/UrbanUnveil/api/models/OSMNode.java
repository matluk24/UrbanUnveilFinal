package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class OSMNode {

	private Long id;
	private String nome;
	private double lat;
	private double lon;
	
	public OSMNode(Long id, String nome, double lat, double lon) {
		this.id=id;
		this.nome=nome;
		this.lat=lat;
		this.lon=lon;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, lat, lon, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OSMNode other = (OSMNode) obj;
		return Objects.equals(id, other.id) && Double.doubleToLongBits(lat) == Double.doubleToLongBits(other.lat)
				&& Double.doubleToLongBits(lon) == Double.doubleToLongBits(other.lon)
				&& Objects.equals(nome, other.nome);
	}
	@Override
	public String toString() {
		return "OSMNode [id=" + id + ", nome=" + nome + ", lat=" + lat + ", lon=" + lon + "]";
	}
	
	
}
