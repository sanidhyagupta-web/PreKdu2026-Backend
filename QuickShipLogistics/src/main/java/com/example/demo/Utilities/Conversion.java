package com.example.demo.Utilities;

import com.example.demo.Models.Package;
import com.example.demo.Models.PackageDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Conversion {
    public Package convert(PackageDto packageDto){
        Package package1 = new Package();
        package1.setId(UUID.randomUUID().toString());
        package1.setDestination(packageDto.getDestination());
        package1.setWeight(packageDto.getWeight());
        package1.setStatus("PENDING");
        package1.setDeliveryType(packageDto.getDeliveryType());
        return package1;
    }
}
