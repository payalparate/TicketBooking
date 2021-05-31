package com.training.exception;

/**
 * @author payal.parate
 *
 */
public class TrainNotFoundException extends Exception {
	String str;

	public TrainNotFoundException(String str) {
		this.str = str;
	}

}
