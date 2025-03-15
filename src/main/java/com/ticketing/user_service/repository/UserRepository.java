package com.ticketing.user_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketing.user_service.dto.SignupRequest;
import com.ticketing.user_service.dto.SignupResponse;
import com.ticketing.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
