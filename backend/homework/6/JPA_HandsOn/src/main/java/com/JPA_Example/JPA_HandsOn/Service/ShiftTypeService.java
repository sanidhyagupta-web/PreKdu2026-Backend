package com.JPA_Example.JPA_HandsOn.Service;

import com.JPA_Example.JPA_HandsOn.Entities.Shift;
import com.JPA_Example.JPA_HandsOn.Entities.ShiftType;
import com.JPA_Example.JPA_HandsOn.Models.ShiftDTO;
import com.JPA_Example.JPA_HandsOn.Models.ShiftTypeDTO;
import com.JPA_Example.JPA_HandsOn.Repository.ShiftTypeRepo;
import com.JPA_Example.JPA_HandsOn.Utilities.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftTypeService {

    @Autowired
    private ShiftTypeRepo shiftTypeRepo;

    @Autowired
    private Conversion conversion;

    public ShiftType addShift(ShiftTypeDTO shiftTypeDTO){
        ShiftType shiftType = conversion.convertToShiftType(shiftTypeDTO);
        shiftTypeRepo.save(shiftType);
        return shiftType;
    }
}
