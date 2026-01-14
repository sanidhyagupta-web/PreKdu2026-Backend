package com.JPA_Example.JPA_HandsOn.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shift_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShiftType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String name;

    @Column(length=500)
    private String description;

    @Column(name = "active_status", nullable=false)
    private Boolean activeStatus;

    @Column(name = "tenant_id", nullable=false)
    private Long tenantId;

    // One ShiftType -> Many Shifts
    @OneToMany(mappedBy = "shiftType", fetch = FetchType.LAZY)
    private List<Shift> shifts = new ArrayList<>();
}