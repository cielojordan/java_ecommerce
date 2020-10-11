package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Lockers;
import app.repositories.LockersRepository;
import java.util.UUID;

@Component
public class LockerComponent {
	
	@Autowired
	LockersRepository lockRepo;
	
	@PostConstruct
	public void init() {
		if(lockRepo.count()==0) {
			Lockers first = new Lockers();
			first.setToken(UUID.randomUUID().toString());
			first.setIsFree(true);
			lockRepo.save(first);
			
			Lockers second = new Lockers();
			second.setToken(UUID.randomUUID().toString());
			second.setIsFree(true);
			lockRepo.save(second);
			
			Lockers fird = new Lockers();
			fird.setToken(UUID.randomUUID().toString());
			fird.setIsFree(true);
			lockRepo.save(fird);
		}
	}

}
