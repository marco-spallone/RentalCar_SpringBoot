package com.stage.rentalcar.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarDTO {
    private Integer id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotNull
    @Min(value = 1900)
    @Max(value = 2023)
    private int year;
    @NotNull
    @PositiveOrZero
    private double price;
    @NotBlank
    @Size(min = 7, max = 8)
    @Pattern(regexp = "[A-Z][A-Z][0-9A-Z]*+")
    private String plate;

}
