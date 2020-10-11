package app.rest;

import java.util.ArrayList;
import java.util.List;

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
import app.components.LockerComponent;
import app.components.ShopComponent;
import app.components.StoreHouseComponent;

@Component
@Path("/Client")
public class ClientController {

	Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	ClientComponent clientCompo;
	
	@Autowired
	LockerComponent lockCompo;
	
	@Autowired
	ShopComponent shopCompo;
	
	@Autowired
	StoreHouseComponent shCompo;
	
	
	@GET
	@Path("/Register")
	@Produces(MediaType.APPLICATION_JSON)
	public String register( @QueryParam("name") String name, 
							@QueryParam("password") String password, 
							@QueryParam("email") String email) {
		
		clientCompo.Register(name, password, email);
		
		return "Succesfully registered!";
	}
	
	@GET
	@Path("/SignIn")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean signIn( @QueryParam("name") String name, 
							@QueryParam("password") String password) {
		clientCompo.signIn(name, password);
		return clientCompo.signIn(name, password);
	}
	
	@GET
	@Path("/lock")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> lock(@QueryParam("clientId") Long clientId) {
		
		return lockCompo.lock(clientId);

	}
	
	@GET
	@Path("/unlock")
	@Produces(MediaType.APPLICATION_JSON)
	public String unlock(@QueryParam("lockerId") Long lockerId, @QueryParam("token") String token) {
		
		if(lockCompo.unlockLocker(lockerId, token)) {
			return "succesfully unlocked!";
		}
		else {return "something is wrong";}		
	}
	
	@GET
	@Path("/availability")
	@Produces(MediaType.APPLICATION_JSON)
	public String askAvailability(@QueryParam("name") String name, @QueryParam("quantity") Integer quantity) {
		
		if(shopCompo.available(name, quantity)) {
			return "Enought quantity you can reserve!";
		}
		else {return "Sorry the shop don't have the item.."; }
		
	}
	
	@GET
	@Path("/reserve")
	@Produces(MediaType.APPLICATION_JSON)
	public String reserveItem(@QueryParam("name") String name, @QueryParam("quantity") Integer quantity) {
		if(shopCompo.reserveItem(name, quantity)) {
			return "Item succesfully reserved!";
		}
		else { return "Sorry unable to reserve item.."; }
	}
	
	@GET
	@Path("/reserveItem")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> reserveFromSH(@QueryParam("name") String name, @QueryParam("quantity") Integer quantity, @QueryParam("clientId") Long clientId) {
		
		List<String> loc = new ArrayList<String>();
		if(shCompo.reserveItem(name, quantity)) {
			
			loc = lockCompo.lock(clientId);
			loc.add("Succesfully reserved item!");
			return loc;
		}
		else { 
			loc.add("Sorry,something went wrong...");
			return loc; 
			}
	}
	
	@GET
	@Path("/LogOut")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean logOut( @QueryParam("id") long id) {
		return clientCompo.logOut(id);
	}
	
	
}
