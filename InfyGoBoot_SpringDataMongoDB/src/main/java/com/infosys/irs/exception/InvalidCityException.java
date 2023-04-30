package com.infosys.irs.exception;

public class InvalidCityException extends InfyGoBootException{
    private static final long serialVersionUID = 1L;
    /**
    * 
     * This Exception is thrown from RegistrationService class with error
    * message RegistrationService.INVALID_EMAIL if the given 
     * email is not matching the constraints given in the regular expression.
    * 
     * 
    *
    */
    public InvalidCityException(String message)
    {
                    super(message);
    }
}