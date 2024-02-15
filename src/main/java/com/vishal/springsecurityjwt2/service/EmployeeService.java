package com.vishal.springsecurityjwt2.service;

import com.vishal.springsecurityjwt2.customresponse.ApiResponse;
import com.vishal.springsecurityjwt2.dto.AuthResponse;
import com.vishal.springsecurityjwt2.dto.LoginRequest;
import com.vishal.springsecurityjwt2.dto.RegisterRequest;
import com.vishal.springsecurityjwt2.entities.Employee;
import com.vishal.springsecurityjwt2.exception.InvalidCredentials;
import com.vishal.springsecurityjwt2.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse registerEmployee(RegisterRequest registerRequest){
        var user = Employee.builder()
                .employeeName(registerRequest.getEmployeeName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .employeePhone(registerRequest.getEmployeePhone())
                .build();
//        if(user==null) {
//            return  new AuthResponse("404", "Bad credentials", null);
//        }


        Employee savedUser = employeeRepo.save(user);
        return AuthResponse.builder().build();
    }

    public ApiResponse loginEmployee (LoginRequest loginRequest){
        try {


            userDetailsService.loadUserByUsername(loginRequest.getEmail());
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//            if (authentication.isAuthenticated() == false) {
////            return new ApiResponse("404", "Bad credentials", null);
//                throw new InvalidCredentials("Error", "Bad credentials");
//
//            }

            var user = employeeRepo.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

            var accessToken = jwtService.generateToken(user);
            var rToken = refreshTokenService.createRefreshToken(user.getUsername());
            var ans = AuthResponse.builder()
                    .token(accessToken)
                    .refreshToken(rToken.getRefreshToken())
                    .build();

            return new ApiResponse("200", "Login_Success", ans);
        }
        catch (AuthenticationException e) {
            // Handle invalid credentials
            throw new InvalidCredentials("Error", "Bad credentials");
        }
    }
}
