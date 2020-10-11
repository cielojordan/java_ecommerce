package app.components;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitCommunication {
	
	@GET("http://localhost:9995/Lockers/unlock")
	public Call<Boolean> unlock(@Query("lockId") Long lockId, @Query("token") String token);

	@GET("http://localhost:9995/Lockers/lock")
	public Call<List<String>> lock(@Query("clientID") Long clientId);
	
	@GET("http://localhost:9996/Shop/availability")
	public Call<Boolean> availability(@Query("name") String name, @Query("quantity") Integer quantity);
	
	@GET("http://localhost:9996/Shop/reserve")
	public Call<Boolean> reserve(@Query("name") String name, @Query("quantity") Integer quantity);
	
	@GET("http://localhost:9997/StoreHouse/reserveItem")
	public Call<Boolean> reserveItem(@Query("name") String name, @Query("quantity") Integer quantity);
	
}
