package com.confRoom.exception;

public class CustomExceptionResponse {
	
	private int statusCode;
    private String message;
 
    public CustomExceptionResponse(String message)
    {
        super();
        this.message = message;
    }
    
    public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomExceptionResponse(int statusCode,String message)
    {
        super();
        this.message = message;
        this.statusCode = statusCode;
    }
}
