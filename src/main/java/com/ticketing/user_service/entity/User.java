package com.ticketing.user_service.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "hashed_password", nullable = false, unique = true)
    private String hashedPassword;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_details_id", referencedColumnName = "account_details_id")
    private AccountDetails accountDetails;

    @Enumerated(EnumType.STRING)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role roles;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;
}
