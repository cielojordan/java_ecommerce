package app.components;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.entities.Client;
import app.entities.Lockers;
import app.repositories.ClientRepository;
import app.repositories.LockersRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class LockerComponent {
	
	@Autowired
	LockersRepository lockRepo;
	
	@Autowired
	ClientRepository clientRepo;
	
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
	
	
	public List<String> lock(Long clientId){
		
		RetrofitCommunication ret = rf.create(RetrofitCommunication.class);
		
		Call<List<String>> c = ret.lock(clientId); 
		try {
		Response<List<String>> response = c.execute();
		return response.body();
		}
		catch(Exception e) {
			List<String> loc = new ArrayList<String>();
			loc.add("Sorry something went wrong...");
			return loc ;
		}
		
		
	}
	
	public Boolean unlockLocker(Long lockerId, String token) {
		
		RetrofitCommunication ret = rf.create(RetrofitCommunication.class);
		
		Call<Boolean> c = ret.unlock(lockerId, token); 
		try {
		Response<Boolean> response = c.execute();
		return response.body();
		}
		
		catch(Exception e) {
			
			return false ;
		}
		
		
	}

}
