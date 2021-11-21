package net.auriema.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("serial")
public class BusinessException extends ResponseStatusException {
	
	public BusinessException(String message) {
		super(HttpStatus.BAD_REQUEST, message, null);
	}
	
}
