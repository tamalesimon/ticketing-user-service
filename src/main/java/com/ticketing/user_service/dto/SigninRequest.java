package com.ticketing.user_service.dto;

import jakarta.annotation.Nonnull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {

    @Nonnull
    private String email;
    @Nonnull
    private String password;
}
