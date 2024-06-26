package com.example.openschool1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressResponseDto {
    private String street;
    private String city;
    private String building;
}
