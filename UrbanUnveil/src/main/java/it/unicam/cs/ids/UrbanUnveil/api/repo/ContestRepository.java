package it.unicam.cs.ids.UrbanUnveil.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.UrbanUnveil.api.models.Contest;

public interface ContestRepository extends JpaRepository<Contest, Long>  {

}
