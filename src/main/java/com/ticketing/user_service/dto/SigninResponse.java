package com.ticketing.user_service.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SigninResponse {
    private UUID userId;
    private String name;
    private String email;
    private String role;
    private String userStatus;
    private String token;
    private Instant expiresAt;
    private String refreshToken;
    private Instant refreshTokenExpiresAt;
}
