package app.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.StoreHouseComponent;
import retrofit2.http.POST;


@Component
@Path("/Manager")
public class ManagerController {

	Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	StoreHouseComponent sHCompo;
	
	@GET
	@Path("/Remove")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean removeItem(@QueryParam("articleName") String articleName, @QueryParam("quantity") int quantity)
	{
		return sHCompo.removeItem(articleName, quantity);
	}
	
	@GET
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addItem(@QueryParam("name") String name, @QueryParam("quantity") int quantity) 
	{
		return sHCompo.addItem(name, quantity);
	}
	
	@GET
	@Path("/addNew")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addNewItem(@QueryParam("name") String name, @QueryParam("price") Double price, 
			@QueryParam("Description") String description, @QueryParam("quantity") int quantity) {
		
		return sHCompo.addNewItem(name, price, description, quantity);
	}
	
	
	
}
