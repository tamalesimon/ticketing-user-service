package com.ticketing.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "preferences")
@Getter
@Setter
public class EventPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
