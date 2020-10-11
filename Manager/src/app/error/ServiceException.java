package app.error;




public class ServiceException extends Exception{
	 
    private Integer httpStatus;
    private String message;
    private Integer code;
    private String developerMessage;
 
 
    public ServiceException(Integer httpStatus, String message, Integer code,String developerMessage) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
        this.developerMessage=developerMessage;
    }
 
 
    public String getDeveloperMessage() {
        return developerMessage;
    }
 
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
 
    public Integer getHttpStatus() {
        return httpStatus;
    }
 
    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public Integer getCode() {
        return code;
    }
 
    public void setCode(Integer code) {
        this.code = code;
    }
 
}
