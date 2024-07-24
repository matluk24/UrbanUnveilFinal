package it.unicam.cs.ids.UrbanUnveil.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.UrbanUnveil.api.models.POI;

public interface POIRepository extends JpaRepository<POI, Long> {

}
