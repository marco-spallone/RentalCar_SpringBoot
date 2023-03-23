package com.stage.rentalcar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer carId;
    private Boolean confirmed;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;


}
