package app.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Client;
import app.entities.Lockers;
import app.repositories.ClientRepository;
import app.repositories.LockersRepository;

@Component
public class LockerComponent {
	
	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	LockersRepository lockRepo;
	
	public List<String> lock(Long clientId) {
		
		List<Lockers> lock = lockRepo.findByIsFree(true);
		lock.get(0).setIsFree(false);
		lock.get(0).setClientId(clientId);
		lockRepo.save(lock.get(0));
		
		java.util.Optional<Client> ou = clientRepo.findById(clientId);
		Client client = ou.get();
		client.setLockerId(lock.get(0).getId());
		client.setToken(lock.get(0).getToken());
		clientRepo.save(client);
		
		List<String> loc = new ArrayList<String>();;
		loc.add(lock.get(0).getId().toString());
		loc.add(lock.get(0).getToken());
		
		return loc;
	}
	
	
	public boolean unlock(Long lockId, String token) {
		
		if(lockRepo.findById(lockId) != null) {
			
			java.util.Optional<Lockers> ou = lockRepo.findById(lockId);
			Lockers lock = ou.get();
			
			if(lock.getToken().equals(token)) {

				java.util.Optional<Client> cl = clientRepo.findById(lock.getClientId());
				Client client = cl.get();
				client.setLockerId(null);
				client.setToken(null);
				clientRepo.save(client);
				
				lock.setIsFree(true);
				lock.setClientId(null);
				lockRepo.save(lock);
				
				return true;
			}
			else { return false; }
		}
		else { return false; }
		
	}

}
