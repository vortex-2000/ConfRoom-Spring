package com.confRoom.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.StringWriter;
import java.io.PrintWriter;

import com.confRoom.service.BookingServiceImpl;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private void logException(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		logger.error(sw.toString());
	}

	
	@ExceptionHandler(value=ConditionFailureException.class)
	public ResponseEntity<Object> handleGlobalConditionFailureException(ConditionFailureException ex){	
		 logException(ex); 
		 CustomExceptionResponse cr= new CustomExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		 return new ResponseEntity<>(cr,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=MissingEntityException.class)
	public ResponseEntity<Object> handleGlobalMissingEntityException(MissingEntityException ex){
		 logException(ex); 
		 CustomExceptionResponse cr= new CustomExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		 return new ResponseEntity<>(cr,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception ex){
		 logException(ex); 
		 CustomExceptionResponse cr= new CustomExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		 return new ResponseEntity<>(cr,HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}
