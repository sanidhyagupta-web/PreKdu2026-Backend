package com.JPA_Example.JPA_HandsOn.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shift")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many Shifts -> One ShiftType
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shift_type_id", nullable = false)
    private ShiftType shiftType;

    @Column(name="date_start", nullable=false)
    private LocalDate dateStart;

    @Column(name="date_end", nullable=false)
    private LocalDate dateEnd;

    @Column(name="time_start", nullable=false)
    private LocalTime timeStart;

    @Column(name="time_end", nullable=false)
    private LocalTime timeEnd;

    @Column(name="tenant_id", nullable=false)
    private Long tenantId;

    // One Shift -> Many ShiftUser rows
    @OneToMany(mappedBy = "shift", fetch = FetchType.LAZY)
    private List<ShiftUser> shiftUsers = new ArrayList<>();
}
