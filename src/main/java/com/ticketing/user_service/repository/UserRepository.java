package com.ticketing.user_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;


import com.ticketing.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @NonNull
    Optional<User> findById(@NonNull UUID id);

    @NonNull
    Optional<User> findByEmail(@NonNull String email);
}
