package app.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Shop;
import app.repositories.ShopRepository;

@Component
public class ClientComponent {
	
	@Autowired
	ShopRepository shopRepo;
	
	public boolean sayAvailability(String name,int quantity) {
		if(shopRepo.findByArticleName(name) !=null) {
			Shop shop = new Shop();
			shop = shopRepo.findByArticleName(name);
			if(shop.getQuantity()<quantity) {
				return false;
			}
			else {
				return true;
				
			}
			
		}
		
		else { return false; }
	}
	
	public boolean reserveItem(String name, int quantity) {
		if(sayAvailability(name, quantity)) {
			Shop shop = new Shop();
			shop = shopRepo.findByArticleName(name);
			shop.setQuantity(shop.getQuantity() - quantity);
			shopRepo.save(shop);
			return true;
		}
		else { return false; }
	}

}
