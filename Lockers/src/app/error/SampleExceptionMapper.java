package app.error;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

// this annotation will tell Jersey to scan for it
@Provider
public class SampleExceptionMapper implements ExceptionMapper<ServiceException>
{

    public Response toResponse(ServiceException ex) 
    {
    	// container object for the error message to be returned
    	// object is used so it can be converted to JSON automatically
        ErrorMessage errorMessage = new ErrorMessage();
        
        // set the attributes
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setCode(ex.getCode());
        errorMessage.setHttpStatus(ex.getHttpStatus());
        
        // copy stacktrace as a string
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setDeveloperMessage(errorStackTrace.toString());
        
        // returns the specific Response we want for this error
        return Response.status(ex.getCode())				// status code usually 5xx
        		       .entity(errorMessage)				// the body
                       .type(MediaType.APPLICATION_JSON)	// tells Jersey to convert to JSON
                       .build();
    }
 

}
