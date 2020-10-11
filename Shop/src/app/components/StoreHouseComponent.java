package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.entities.Articles;
import app.entities.Shop;
import app.entities.StoreHouse;
import app.repositories.ArticlesRepository;
import app.repositories.ShopRepository;
import app.repositories.StoreHouseRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class StoreHouseComponent {
	
	@Autowired
	StoreHouseRepository sHRepo;
	
	@Autowired
	ShopRepository shopRepo;
	
	@Autowired
	ArticlesRepository artRepo;
	
	Retrofit rf;
	
	@PostConstruct
	public void initRetrofit()
	{
		Gson gson = new GsonBuilder()       
				         .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				         .create();
		rf = new Retrofit.Builder().baseUrl("http://localhost")   // even if not using base urls, i.e. all absolute URLs you still need to specify one which will be ignored for absolute URLs        
						           .addConverterFactory(GsonConverterFactory.create(gson))
						           .build();
	}
	
	
	public boolean askItem(String name, int quantity) {
		StoreHouse sH = new StoreHouse();
		if(sHRepo.findByArticleName(name) != null) {
			sH = sHRepo.findByArticleName(name);
			if(sH.getQuantity()<quantity) {
				return false;
			}
			else {
				Shop shop = new Shop();
				if(shopRepo.findByArticleName(name) != null) {
					shop = shopRepo.findByArticleName(name);
					shop.setQuantity(quantity + shop.getQuantity());
					sH.setQuantity(sH.getQuantity()-quantity);
					sHRepo.save(sH);
					shopRepo.save(shop);
					return true;
				}
				else {
					Articles article = new Articles();
					article = artRepo.findByName(name);
					shop.setArticleName(name);
					shop.setArticlePrice(article.getPrice());
					shop.setIdArticles(article.getId());
					shop.setQuantity(quantity);
					sH.setQuantity(sH.getQuantity()-quantity);
					sHRepo.save(sH);
					shopRepo.save(shop);
					return true;
				}
				
			}
		}
		else { return false;}
	}

	public boolean askingItem(String name, Integer quantity) {
		
		RetrofitCommunication ret = rf.create(RetrofitCommunication.class);
		
		Call<Boolean> c = ret.reserveItem(name, quantity); 
		try {
		Response<Boolean> response = c.execute();
		return response.body();
		}
		
		catch(Exception e) {
			
			return false ;
		}
	}
	
}
