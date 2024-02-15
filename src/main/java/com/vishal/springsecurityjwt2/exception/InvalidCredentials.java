package com.vishal.springsecurityjwt2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidCredentials extends RuntimeException{

    private String status;
    private String message;
}
