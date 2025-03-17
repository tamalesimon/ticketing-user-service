package com.ticketing.user_service.dto;


import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @Nonnull
    String name;
    @Nonnull
    String email;
    @Nonnull
    String password;
    @Nonnull
    String role;
}
