package com.stage.rentalcar.dto;

import lombok.Data;

@Data
public class CarDTO {
    private Integer id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private String plate;

}
