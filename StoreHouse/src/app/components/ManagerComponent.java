package app.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Articles;
import app.entities.StoreHouse;
import app.repositories.ArticlesRepository;
import app.repositories.StoreHouseRepository;

@Component
public class ManagerComponent {

	@Autowired
	StoreHouseRepository sHRepo;
	
	@Autowired
	ArticlesRepository artRepo;
	
	public Boolean addItem(String name, int quantity) {
		System.out.println("quantity: (addItem) "+quantity );
		StoreHouse item = new StoreHouse();
		if(sHRepo.findByArticleName(name)!= null) {
			item = sHRepo.findByArticleName(name);
			item.setQuantity(item.getQuantity()+quantity);
			sHRepo.save(item);
		}
		else {
			Articles item2 = new Articles();
			item2 = artRepo.findByName(name);
			item.setIdArticle(item2.getId());
			item.setArticleName(item2.getName());
			item.setArticlePrice(item2.getPrice());
			item.setQuantity(quantity);
			sHRepo.save(item);
		}
		return true;
	}

	public Boolean removeItem(String name, int quantity) {
		StoreHouse item = new StoreHouse();
		item = sHRepo.findByArticleName(name);
		if(item.getQuantity()>quantity) {
			item.setQuantity(item.getQuantity()-quantity);
			sHRepo.save(item);
		}
		else {
			sHRepo.delete(item);
		}
		return true;
	}
	
	public Boolean addNew(String name, Double price, String description, int quantity) {
		Articles article = new Articles();
		article.setName(name);
		article.setPrice(price);
		article.setDescription(description);
		artRepo.save(article);
			
		System.out.println("quantity(new):" + quantity);
		
		return addItem(name, quantity);
		
	}
}
