package com.vishal.springsecurityjwt2.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;;

@Data
@Builder
public class RefreshToken {
    private String refreshToken;
    private Instant expireTime;

}
