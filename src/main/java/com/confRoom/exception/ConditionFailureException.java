package com.confRoom.exception;

public class ConditionFailureException extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ConditionFailureException() {}

	public ConditionFailureException(String msg)
	{
		super(msg);
		this.message = msg;
	}
}
