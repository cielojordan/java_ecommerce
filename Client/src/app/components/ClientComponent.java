package app.components;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Client;
import app.repositories.ClientRepository;

@Component
public class ClientComponent {

	@Autowired
	ClientRepository clientRepo;
	
	public void Register(String name, String password, String email) {
		Client client = new Client();
		client.setEmail(email);
		client.setName(name);
		client.setPassword(password);
		clientRepo.save(client);
	}
	
	public boolean signIn(String name, String password) {
		if(clientRepo.findByNameAndPassword(name, password) != null) {
			Client client = clientRepo.findByNameAndPassword(name, password);
			String token = UUID.randomUUID().toString();
			client.setTokenLog(token);
			clientRepo.save(client);
			return true;
		}
		else { return false; }
	}
	
	public boolean logOut(long id) {
		if(clientRepo.findById(id) != null) {
			java.util.Optional<Client> ou = clientRepo.findById(id);
			Client client = ou.get();
			String token = null;
			client.setTokenLog(token);
			clientRepo.save(client);
			return true;
		}
		else { return false; }
	}
	
}
