package com.training.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author payal.parate
 *
 */
@RestControllerAdvice
public class ControllerAdvisor {
	/**
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(ex.getLocalizedMessage()));
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(TrainNotFoundException.class)
	public ResponseEntity<?> handleException(TrainNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train not available for the requested data");
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleException(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not available for the requested Id");
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleException(NullPointerException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Values cannot be null");
	}

}
