package app.components;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitCommunication {
	
	@GET("http://localhost:9997/StoreHouse/sendItem")
	public Call<Boolean> reserveItem(@Query("name") String name, @Query("quantity") Integer quantity);
	
}
