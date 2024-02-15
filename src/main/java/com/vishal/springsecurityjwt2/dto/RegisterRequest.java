package com.vishal.springsecurityjwt2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String employeeName;
    private String email;
    private String password;
    private String employeePhone;
}
