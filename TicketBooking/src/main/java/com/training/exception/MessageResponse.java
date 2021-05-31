package com.training.exception;

/**
 * @author payal.parate
 *
 */
public class MessageResponse {
	private String message;

	/**
	 * @param message
	 */
	public MessageResponse(String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
