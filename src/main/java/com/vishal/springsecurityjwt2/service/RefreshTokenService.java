package com.vishal.springsecurityjwt2.service;

import com.vishal.springsecurityjwt2.dto.RefreshToken;
import com.vishal.springsecurityjwt2.entities.Employee;
import com.vishal.springsecurityjwt2.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final EmployeeRepo employeeRepo;


    public RefreshToken createRefreshToken(String username) {
        Employee employee = employeeRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));

        RefreshToken refreshToken = null;

            long refreshTokenValidity = 2*60 * 1000;
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expireTime(Instant.now().plusMillis(refreshTokenValidity))
                    .build();

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(RefreshToken refreshToken) {

        if (refreshToken.getExpireTime().compareTo(Instant.now()) < 0) {
            throw new RuntimeException("Refresh Token expired");
        }

        return refreshToken;
    }
}
