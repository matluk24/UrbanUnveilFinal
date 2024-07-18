package it.unicam.cs.ids.UrbanUnveil.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unicam.cs.ids.UrbanUnveil.api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	User findByPassword(String password);
}
