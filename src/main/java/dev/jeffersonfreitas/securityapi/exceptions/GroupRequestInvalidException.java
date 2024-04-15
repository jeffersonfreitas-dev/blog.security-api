package dev.jeffersonfreitas.securityapi.exceptions;

public class GroupRequestInvalidException extends RuntimeException{

    public GroupRequestInvalidException(String message){
        super(message);
    }
}
