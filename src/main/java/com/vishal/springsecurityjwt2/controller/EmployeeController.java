package com.vishal.springsecurityjwt2.controller;

import com.vishal.springsecurityjwt2.customresponse.ApiResponse;
import com.vishal.springsecurityjwt2.dto.AuthResponse;
import com.vishal.springsecurityjwt2.dto.LoginRequest;
import com.vishal.springsecurityjwt2.dto.RegisterRequest;
import com.vishal.springsecurityjwt2.entities.Employee;
import com.vishal.springsecurityjwt2.service.EmployeeService;
import com.vishal.springsecurityjwt2.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

        private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        AuthResponse authResponse = employeeService.registerEmployee(registerRequest);

        return  new ResponseEntity<>(employeeService.registerEmployee(registerRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {


        return ResponseEntity.ok(employeeService.loginEmployee(loginRequest));
    }

}
