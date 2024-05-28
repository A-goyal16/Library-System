package org.paycorp.librarysystem.Exception;

import org.paycorp.helper.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyException {
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseStructure exceptionhandler(DataNotFoundException exception)
	{
		ResponseStructure response=new ResponseStructure();
		response.setData(null);
		response.setMessage("data is not found");
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		return response;
	}
}
