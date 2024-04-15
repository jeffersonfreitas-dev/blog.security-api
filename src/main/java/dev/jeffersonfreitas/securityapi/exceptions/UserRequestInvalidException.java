package dev.jeffersonfreitas.securityapi.exceptions;

public class UserRequestInvalidException extends RuntimeException{

    public UserRequestInvalidException(String message){
        super(message);
    }
}
