package com.ticketing.user_service.dto;

import java.util.UUID;

import com.ticketing.user_service.entity.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponse {

    private UUID userId;
    private String name;
    private String email;
    private UserStatus status;
}
