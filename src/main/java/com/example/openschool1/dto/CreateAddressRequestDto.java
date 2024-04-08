package com.example.openschool1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateAddressRequestDto {
    @NotBlank(message = "Street cannot be null")
    private String street;
    @NotBlank(message = "City cannot be null")
    private String city;
    @NotBlank(message = "Building cannot be null")
    private String building;
}
