package com.confRoom.exception;

public class MissingEntityException extends RuntimeException{
	
	 
		private String message;

		public MissingEntityException() {}

		public MissingEntityException(String msg)
		{
			super(msg);
			this.message = msg;
		}
}
