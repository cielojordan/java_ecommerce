package app.rest;

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

import app.components.LockerComponent;

@Component
@Path("/Lockers")
public class LockersController {

	Logger logger = LoggerFactory.getLogger(LockersController.class);
	
	@Autowired
	LockerComponent lockCompo;
	
	@GET
	@Path("/lock")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> lock(@QueryParam("clientID") Long clientId) {
		
			return lockCompo.lock(clientId);

	}
	
	@GET
	@Path("/unlock")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean unlock(@QueryParam("lockId") Long lockId, @QueryParam("token") String token) {
		if(lockCompo.unlock(lockId, token)) {
			return true;
			
		}
			else {return false;}		
	}
	
}
