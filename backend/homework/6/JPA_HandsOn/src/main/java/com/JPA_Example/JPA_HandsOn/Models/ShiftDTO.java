package com.JPA_Example.JPA_HandsOn.Models;

import com.JPA_Example.JPA_HandsOn.Entities.ShiftType;
import com.JPA_Example.JPA_HandsOn.Entities.ShiftUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShiftDTO {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shift_type_id", nullable = false)
    private ShiftType shiftType;

    @Column(name="tenant_id", nullable=false)
    private Long tenantId;

    // One Shift -> Many ShiftUser rows
    @OneToMany(mappedBy = "shift", fetch = FetchType.LAZY)
    private List<ShiftUser> shiftUsers = new ArrayList<>();

}
