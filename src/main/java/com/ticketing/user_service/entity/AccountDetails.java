package com.ticketing.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "account_details")
@Getter
@Setter
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_details_id")
    private Long id;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String country;

    private boolean isNonLocked = true;

    private String profilePicture;

    @ManyToMany
    @JoinTable(name = "preferences", joinColumns = @JoinColumn(name = "account_details_id"), inverseJoinColumns =@JoinColumn(name = "preference_id"))
    @Column(name = "preference")
    private Set<EventPreference> preferences;

}
