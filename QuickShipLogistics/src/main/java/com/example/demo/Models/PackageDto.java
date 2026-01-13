package com.example.demo.Models;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class PackageDto {
    @NotNull(message = "Destination cannot be empty")
    @Size(min = 2,max = 50,message = "Destination must be between 2 and 50 characters")
    private String destination;

    @NotNull
    @Min(value = 10 , message = "Weight should be more than 2 kg")
    @Max(value = 500 , message = "Weight should be less than 500 kg")
    private double weight;

    @NotNull
    private String deliveryType;

}
