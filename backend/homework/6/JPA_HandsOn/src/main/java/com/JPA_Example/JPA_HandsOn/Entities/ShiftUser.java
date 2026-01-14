package com.JPA_Example.JPA_HandsOn.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shift_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShiftUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many ShiftUser -> One Shift
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="shift_id", nullable=false)
    private Shift shift;

    // Many ShiftUser -> One User
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name="tenant_id", nullable=false)
    private Long tenantId;
}