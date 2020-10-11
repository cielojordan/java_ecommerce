package app.components;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitCommunication {
	
	@GET("http://localhost:9997/StoreHouse/addNew")
	public Call<Boolean> addNewItem(@Query("name") String name, @Query("price") Double price, 
			@Query("Description") String description, @Query("quantity") Integer quantity);
	
	@GET("http://localhost:9997/StoreHouse/add")
	public Call<Boolean>  Add(@Query("name") String name,@Query("quantity") Integer quantity);
	
	@GET("http://localhost:9997/StoreHouse/Remove")
	public Call<Boolean> remove(@Query("articleName") String name,@Query("quantity") Integer quantity);


	
}
