package com.infosys.irs.exception;

public class InvalidUserIdException extends InfyGoBootException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * This Exception is thrown from RegistrationService class with error message
	 * RegistrationService.INVALID_USER_ID if the given userId is not matching the
	 * constraints given in the form of regular expression.
	 * 
	 * 
	 *
	 */
	public InvalidUserIdException(String message) {
		super(message);
	}
}