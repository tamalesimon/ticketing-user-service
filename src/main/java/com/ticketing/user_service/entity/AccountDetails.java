package com.ticketing.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "account_details")
@Getter
@Setter
public class AccountDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_details_id")
    private UUID id;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String country;

    private boolean isNonLocked = true;

    private String profilePicture;

    @ManyToMany
    @JoinTable(name = "preferences", joinColumns = @JoinColumn(name = "account_details_id"), inverseJoinColumns = @JoinColumn(name = "preference_id"))
    @Column(name = "preference")
    private List<EventPreference> preferences;

}
