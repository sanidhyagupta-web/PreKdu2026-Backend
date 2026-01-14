package com.JPA_Example.JPA_HandsOn.Service;

import com.JPA_Example.JPA_HandsOn.Entities.ShiftUser;
import com.JPA_Example.JPA_HandsOn.Repository.ShiftUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftUserService {

    @Autowired
    private ShiftUserRepo shiftUserRepo;

    public ShiftUser addShiftUser(ShiftUser shiftUser){
       return shiftUserRepo.save(shiftUser);
    }
}
