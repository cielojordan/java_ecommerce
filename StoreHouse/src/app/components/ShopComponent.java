package app.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Articles;
import app.entities.Shop;
import app.entities.StoreHouse;
import app.repositories.ArticlesRepository;
import app.repositories.ShopRepository;
import app.repositories.StoreHouseRepository;

@Component
public class ShopComponent {
	
	@Autowired
	ShopRepository shopRepo;
	
	@Autowired
	StoreHouseRepository sHRepo;
	
	@Autowired
	ArticlesRepository artRepo;
	
	public boolean sendItemToShop(String name, int quantity) {
		Shop shop = new Shop();
		StoreHouse sH = new StoreHouse();
		
		if(sHRepo.findByArticleName(name) != null) {
			sH = sHRepo.findByArticleName(name);
			if(sH.getQuantity()<quantity) {
				return false;
			}
			else {
				if(shopRepo.findByArticleName(name) != null) {
					shop = shopRepo.findByArticleName(name);
					shop.setQuantity(shop.getQuantity()+quantity);
					shopRepo.save(shop);
					sH.setQuantity(sH.getQuantity()-quantity);
					sHRepo.save(sH);
					return true;
				}
				else {
					Articles article = new Articles();
					article = artRepo.findByName(name);
					shop.setArticleName(name);
					shop.setQuantity(quantity);
					shop.setArticlePrice(article.getPrice());
					shop.setIdArticles(article.getId());
					shopRepo.save(shop);
					sH.setQuantity(sH.getQuantity()-quantity);
					sHRepo.save(sH);
					return true;
				}
			}
		 
	}
	
	else { return false; }
	}

}
