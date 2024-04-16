package dev.jeffersonfreitas.securityapi.exceptions;

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }
}
