package com.stage.rentalcar.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarDTO {
    private Integer id;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    @Min(value = 1900)
    @Max(value = 2023)
    private int year;
    @NotNull
    @PositiveOrZero
    private double price;
    @NotNull
    @Size(min = 7, max = 8)
    private String plate;

}
