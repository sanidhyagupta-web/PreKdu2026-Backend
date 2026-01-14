package com.JPA_Example.JPA_HandsOn.Service;

import com.JPA_Example.JPA_HandsOn.Entities.Shift;
import com.JPA_Example.JPA_HandsOn.Models.ShiftDTO;
import com.JPA_Example.JPA_HandsOn.Repository.ShiftRepo;
import com.JPA_Example.JPA_HandsOn.Utilities.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepo shiftRepo;

    @Autowired
    private Conversion conversion;

    public Shift addShift(ShiftDTO shiftDTO){
        Shift shift = conversion.convertToShift(shiftDTO);
        shiftRepo.save(shift);
        return shift;
    }

    public List<Shift> getTop3NewYearShifts() {
        LocalDate start = LocalDate.of(2023, 1, 1);
        LocalDate endBy = LocalDate.of(2023, 1, 25);

        return shiftRepo.findNewYearTopShifts(start, endBy, PageRequest.of(0, 3));
    }

}
