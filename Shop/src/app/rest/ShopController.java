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

import app.components.ClientComponent;
import app.components.StoreHouseComponent;

@Component
@Path("/Shop")
public class ShopController {

	Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	StoreHouseComponent sHCompo;
	
	@Autowired
	ClientComponent clientCompo;
	
	@GET
	@Path("/askItem")
	@Produces(MediaType.APPLICATION_JSON)
	public String askItem(@QueryParam("name") String name,@QueryParam("quantity") int quantity) {
		if(sHCompo.askItem(name, quantity)) {
			return "Article added to store";
		}
		else { return "Sorry article can't be added to store"; }
	}
	
	@GET
	@Path("availability")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean askAvailability(@QueryParam("name") String name, @QueryParam("quantity") Integer quantity) {
		
		return clientCompo.sayAvailability(name, quantity);
		
	}
	
	@GET
	@Path("reserve")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean reserve(@QueryParam("name") String name, @QueryParam("quantity") Integer quantity) {
		
		return clientCompo.reserveItem(name, quantity);
	}
	
	@GET
	@Path("askingItem")
	@Produces(MediaType.APPLICATION_JSON)
	public String askItem(@QueryParam("name") String name, @QueryParam("quantity") Integer quantity) {
		
		if(sHCompo.askingItem(name, quantity)) {
			return "Succesfully get item from Store House";
		}
		else { return "ERROR :("; }
	}
	
}
