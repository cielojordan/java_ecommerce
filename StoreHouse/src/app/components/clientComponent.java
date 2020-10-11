package app.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.StoreHouse;
import app.repositories.StoreHouseRepository;

@Component
public class clientComponent {
	
	@Autowired
	StoreHouseRepository shRepo;
	
	public Boolean reserveItem(String name, Integer quantity) {
		
		StoreHouse sH = shRepo.findByArticleName(name);
		if(sH.getQuantity()<quantity) {
			return false;
		}
		else {
			sH.setQuantity(sH.getQuantity()-quantity);
			shRepo.save(sH);
			return true;
		}
	}

}
