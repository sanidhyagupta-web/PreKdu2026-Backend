package com.JPA_Example.JPA_HandsOn.Models;

import com.JPA_Example.JPA_HandsOn.Entities.Shift;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShiftTypeDTO {

    @Column(nullable=false, length=120)
    private String name;

    @Column(length=500)
    private String description;

    @Column(name = "tenant_id", nullable=false)
    private Long tenantId;

    // One ShiftType -> Many Shifts
    @OneToMany(mappedBy = "shiftType", fetch = FetchType.LAZY)
    private List<Shift> shifts = new ArrayList<>();
}
