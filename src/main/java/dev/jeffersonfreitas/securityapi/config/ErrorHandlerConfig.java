package dev.jeffersonfreitas.securityapi.config;

import dev.jeffersonfreitas.securityapi.dto.error.CustomErrorResponse;
import dev.jeffersonfreitas.securityapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerConfig {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GroupNotFoundException.class)
    public CustomErrorResponse handleGroupNotFoundException(GroupNotFoundException ex){
        return CustomErrorResponse.create(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public CustomErrorResponse handleUserNotFoundException(UserNotFoundException ex){
        return CustomErrorResponse.create(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GroupRequestInvalidException.class)
    public CustomErrorResponse handleGroupRequestInvalidException(GroupRequestInvalidException ex){
        return CustomErrorResponse.create(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserRequestInvalidException.class)
    public CustomErrorResponse handleUserRequestInvalidException(UserRequestInvalidException ex){
        return CustomErrorResponse.create(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public CustomErrorResponse handleBusinessException(BusinessException ex){
        return CustomErrorResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = new StringBuilder("Erros de validação: ");
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            errors.append(fieldError.getField() + ": " + fieldError.getDefaultMessage()+" | ");
        }
        return CustomErrorResponse.create(HttpStatus.BAD_REQUEST.value(), errors.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CustomErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        var error = "O parâmetro " + ex.getParameterName() + " deve ser informado!";
        return CustomErrorResponse.create(HttpStatus.BAD_REQUEST.value(), error);
    }
}
