package com.example.openschool1.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserRequestDto {
    private Integer id;
    @NotBlank(message = "Login cannot be empty")
    private String login;
    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    @NotBlank(message = "Middle name cannot be empty")
    private String middleName;
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;
    @Min(value = 0, message = "Age must be greater than or equal to 0")
    @Max(value = 150, message = "Age must be lower than or equal to 150")
    private Integer age;
    @NotNull(message = "Address cannot be null")
    private CreateAddressRequestDto address;
}