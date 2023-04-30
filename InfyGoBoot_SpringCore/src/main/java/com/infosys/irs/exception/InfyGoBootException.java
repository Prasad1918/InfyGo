package com.infosys.irs.exception;

/**
 * All other user defined exception classes will inherit this exception class.
 *
 */
public class InfyGoBootException extends Exception {
	private static final long serialVersionUID = 1L;

	public InfyGoBootException(String message) {
		super(message);
	}
}