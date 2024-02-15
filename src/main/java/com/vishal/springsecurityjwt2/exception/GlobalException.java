package com.vishal.springsecurityjwt2.exception;

import com.vishal.springsecurityjwt2.customresponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(InvalidCredentials.class)
    public ApiResponse badCredentialExceptionHandler(InvalidCredentials e){
        return new ApiResponse("Bad_Credential",e.getMessage(),null);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiResponse> insufficientAuthExceptionHandler(InsufficientAuthenticationException e){
        return new ResponseEntity<>(new ApiResponse("In_Sufficient_Data",e.getMessage(),null), HttpStatus.OK);
    }

    @ExceptionHandler(InvalidTokenInHeaderException.class)
    public ResponseEntity<ApiResponse> inValidTokenInHeaderException(InvalidTokenInHeaderException e){
        return new ResponseEntity<>(new ApiResponse("In_Valid_Token",e.getMessage(),null), HttpStatus.OK);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse> usernameNotFoundException(UsernameNotFoundException e){
        return new ResponseEntity<>(new ApiResponse("user_NameNotFound",e.getMessage(),null), HttpStatus.OK);
    }
}
