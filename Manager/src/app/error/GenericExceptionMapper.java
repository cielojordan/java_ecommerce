package app.error;


import java.io.PrintWriter;
import java.io.StringWriter;
 
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 
import org.springframework.http.HttpStatus;

import app.error.ErrorMessage;
 
//this annotation will tell Jersey to scan for it 
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
 
    public Response toResponse(Throwable ex) {
    	
    	// container object
        ErrorMessage errorMessage = new ErrorMessage();
        
        // set fields
        errorMessage.setMessage(ex.getMessage());
        
        // copy stacktrace as a string
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setDeveloperMessage(errorStackTrace.toString());
        
        
        // returns the specific Response we want for this error
        return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        			   .entity(errorMessage)
        			   .type(MediaType.APPLICATION_JSON)
        			   .build();
    }
 
}