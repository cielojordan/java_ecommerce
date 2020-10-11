package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.entities.Articles;
import app.entities.StoreHouse;
import app.repositories.ArticlesRepository;
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
	ArticlesRepository artRepo;
	
	Retrofit rf;
	
	@PostConstruct
	public void init() {
		if(sHRepo.count()==0) {
			StoreHouse keyboard = new StoreHouse();
			Articles keyboard2 = new Articles();
			keyboard2 = artRepo.findByName("keyboard");
			keyboard.setIdArticle(keyboard2.getId());
			keyboard.setArticleName(keyboard2.getName());
			keyboard.setArticlePrice(keyboard2.getPrice());
			keyboard.setQuantity(400);
			sHRepo.save(keyboard);
			
			StoreHouse mouse = new StoreHouse();
			Articles mouse2 = new Articles();
			mouse2 = artRepo.findByName("mouse");
			mouse.setArticleName(mouse2.getName());
			mouse.setIdArticle(mouse2.getId());
			mouse.setArticlePrice(mouse2.getPrice());
			mouse.setQuantity(400);
			sHRepo.save(mouse);
			
			StoreHouse speaker = new StoreHouse();
			Articles speaker2 = new Articles();
			speaker2 = artRepo.findByName("speaker");
			speaker.setIdArticle(speaker2.getId());
			speaker.setArticleName(speaker2.getName());
			speaker.setArticlePrice(speaker2.getPrice());
			speaker.setQuantity(400);
			sHRepo.save(speaker);
		}
		
	}
	
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
	
	public Boolean addItem(String name, Integer quantity) {

		RetrofitCommunication ret = rf.create(RetrofitCommunication.class);
		
		Call<Boolean> c = ret.Add(name, quantity); 
		try {
		Response<Boolean> response = c.execute();
		return response.body();
		}
		
		catch(Exception e) {
			
			return false ;
		}
		
	}
	
	
	public Boolean removeItem(String name, Integer quantity) {

		RetrofitCommunication ret = rf.create(RetrofitCommunication.class);
		
		Call<Boolean> c = ret.remove(name, quantity); 
		try {
		Response<Boolean> response = c.execute();
		return response.body();
		}
		
		catch(Exception e) {
			
			return false ;
		}
		
	}

	public Boolean addNewItem(String name, Double price, String description, int quantity) {
		
		RetrofitCommunication ret = rf.create(RetrofitCommunication.class);
		
		Call<Boolean> c = ret.addNewItem(name, price, description, quantity); 
		try {
		Response<Boolean> response = c.execute();
		return response.body();
		}
		
		catch(Exception e) {
			
			return false ;
		}
	}
}
