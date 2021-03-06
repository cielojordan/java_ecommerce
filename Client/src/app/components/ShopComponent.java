package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.entities.Shop;
import app.repositories.ShopRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class ShopComponent {
	
	@Autowired
	ShopRepository shopRepo;
	
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
	
	
	public Boolean available(String name,Integer quantity) {
		
	RetrofitCommunication ret = rf.create(RetrofitCommunication.class);
	
	Call<Boolean> c = ret.availability(name, quantity); 
	try {
	Response<Boolean> response = c.execute();
	return response.body();
	}
	
	catch(Exception e) {
		
		return false ;
	}
}
	
	public Boolean reserveItem(String name, Integer quantity) {
		
		RetrofitCommunication ret = rf.create(RetrofitCommunication.class);
		
		Call<Boolean> c = ret.reserve(name, quantity); 
		try {
		Response<Boolean> response = c.execute();
		return response.body();		}
		
		catch(Exception e) {
			
			return false ;
		}
	}

}
