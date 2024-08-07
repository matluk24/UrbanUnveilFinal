package it.unicam.cs.ids.UrbanUnveil.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;

public interface ContentRepository extends JpaRepository<Content, Long>  {

}
