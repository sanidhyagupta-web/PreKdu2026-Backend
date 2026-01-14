package com.JPA_Example.JPA_HandsOn.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String username;

    @Column(name="logged_in_status", nullable=false)
    private Boolean loggedInStatus;

    @Column(name="time_zone", nullable=false, length=60)
    private String timeZone;

    @Column(name="tenant_id", nullable=false)
    private Long tenantId;

    // One User -> Many ShiftUser rows
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ShiftUser> shiftUsers = new ArrayList<>();
}
