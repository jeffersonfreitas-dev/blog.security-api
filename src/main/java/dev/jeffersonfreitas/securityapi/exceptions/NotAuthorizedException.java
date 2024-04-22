package dev.jeffersonfreitas.securityapi.exceptions;

public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException(String message){
        super(message);
    }
}
