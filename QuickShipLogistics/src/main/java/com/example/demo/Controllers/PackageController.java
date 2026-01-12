package com.example.demo.Controllers;

import com.example.demo.Models.Package;
import com.example.demo.Models.PackageDto;
import com.example.demo.Service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouse")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping("/packages")
    @Operation(summary = "Controller for Adding a package",
                description = "Here the package status will be changed by a background thread , while the main thread " +
                        "will return the response to user for optimization purposes. Roles allowed :: MANAGER")
    public ResponseEntity<Package> addPackage(@Valid @RequestBody PackageDto packageDto){
        Package pack = packageService.addPackage(packageDto);
        return new ResponseEntity<>(pack, HttpStatus.ACCEPTED);
    }

    @GetMapping("/analytics/revenue")
    @Operation(summary = "Controller for Calculating the revenue",
                description = "Roles Allowed :: MANAGER , DRIVER")
    public ResponseEntity<Double> getRevenue(){
        Double revenue = packageService.getRevenue();
        return new ResponseEntity<>(revenue,HttpStatus.OK);
    }
}
