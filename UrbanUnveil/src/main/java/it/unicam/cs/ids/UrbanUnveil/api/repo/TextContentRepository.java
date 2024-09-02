package it.unicam.cs.ids.UrbanUnveil.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;

public interface TextContentRepository extends JpaRepository<TextContent, Long> {

}
