package com.JPA_Example.JPA_HandsOn.Utilities;

import com.JPA_Example.JPA_HandsOn.Entities.Shift;
import com.JPA_Example.JPA_HandsOn.Entities.ShiftType;
import com.JPA_Example.JPA_HandsOn.Entities.ShiftUser;
import com.JPA_Example.JPA_HandsOn.Models.ShiftDTO;
import com.JPA_Example.JPA_HandsOn.Models.ShiftTypeDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Component
public class Conversion {


    public Shift convertToShift(ShiftDTO shiftDTO){
        Shift shift = new Shift();
        shift.setShiftType(shiftDTO.getShiftType());
        // OR if DTO has only ID:
        // shift.setShiftType(new ShiftType(shiftDTO.getShiftTypeId()));

        shift.setDateStart(LocalDate.now());
        shift.setDateEnd(LocalDate.now());

        shift.setTimeStart(LocalTime.now());
        shift.setTimeEnd(LocalTime.now().plusHours(8));

        shift.setTenantId(shiftDTO.getTenantId());

        // Initialize empty collection
        shift.setShiftUsers(new ArrayList<>());

        return shift;
    }


    public ShiftType convertToShiftType(ShiftTypeDTO shiftTypeDTO){
        ShiftType shiftType = new ShiftType();

        // DO NOT set ID manually (JPA will handle it)

        shiftType.setName(shiftTypeDTO.getName());
        shiftType.setDescription(shiftTypeDTO.getDescription());
        shiftType.setActiveStatus(false);
        shiftType.setTenantId(shiftTypeDTO.getTenantId());

        return shiftType;
    }



}
