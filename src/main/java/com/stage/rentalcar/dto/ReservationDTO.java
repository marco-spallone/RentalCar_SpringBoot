package com.stage.rentalcar.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {
    private Integer id;
    private Integer userId;
    private Integer carId;
    private boolean confirmed;
    private LocalDate startDate;
    private LocalDate endDate;


}
