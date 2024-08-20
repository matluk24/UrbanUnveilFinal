package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class OSMNode {

	private Long place_id;
	private String nome;
	private double lat;
	private double lon;
	
	public OSMNode(Long place_id, String nome, double lat, double lon) {
		this.place_id=place_id;
		this.nome=nome;
		this.lat=lat;
		this.lon=lon;
	}
	
	public Long getPlaceId() {
		return place_id;
	}
	public void setPlaceId(Long place_id) {
		this.place_id = place_id;
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
		return Objects.hash(place_id, lat, lon, nome);
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
		return Objects.equals(place_id, other.place_id) && Double.doubleToLongBits(lat) == Double.doubleToLongBits(other.lat)
				&& Double.doubleToLongBits(lon) == Double.doubleToLongBits(other.lon)
				&& Objects.equals(nome, other.nome);
	}
	@Override
	public String toString() {
		return "OSMNode [place_id=" + place_id + ", nome=" + nome + ", lat=" + lat + ", lon=" + lon + "]";
	}
	
	
}
