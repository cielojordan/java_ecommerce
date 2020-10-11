package app.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.ManagerComponent;
import app.components.ShopComponent;
import app.components.clientComponent;

@Component
@Path("/StoreHouse")
public class StoreHouseController {
	
	Logger logger = LoggerFactory.getLogger(StoreHouseController.class);

	@Autowired
	ShopComponent shopCompo;
	
	@Autowired
	clientComponent clientCompo;
	
	@Autowired
	ManagerComponent manCompo;
	
	@GET
	@Path("/sendItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean sendItem(@QueryParam("name") String name, @QueryParam("quantity") int quantity) {
		
		return shopCompo.sendItemToShop(name, quantity);
		
	}
	
	@GET
	@Path("/reserveItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean reserveItem(@QueryParam("name") String name, @QueryParam("quantity") Integer quantity) {
		
		return clientCompo.reserveItem(name, quantity);
		
	}
	
	@GET
	@Path("/Remove")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeItem(@QueryParam("articleName") String articleName, @QueryParam("quantity") int quantity)
	{
		return manCompo.removeItem(articleName, quantity);
		
	}
	
	@GET
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addItem(@QueryParam("name") String name, @QueryParam("quantity") int quantity) {
		return manCompo.addItem(name, quantity);
	}
	
	@GET
	@Path("/addNew")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addNewItem(@QueryParam("name") String name, @QueryParam("price") Double price, 
			@QueryParam("Description") String description, @QueryParam("quantity") int quantity) {
		
		return manCompo.addNew(name, price, description, quantity);
	}
}
