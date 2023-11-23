package com.confRoom.exception;

public class BookingStartException extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BookingStartException() {}

	public BookingStartException(String msg)
	{
		super(msg);
		this.message = msg;
	}
}
