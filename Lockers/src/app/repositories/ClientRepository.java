package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	public Client findByNameAndPassword(String name, String password);

}
