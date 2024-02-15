package com.vishal.springsecurityjwt2.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsufficientAuthenticationException extends RuntimeException{
    private  String status;
    private  String message;
}
