package com.stage.rentalcar.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FreeCarRequest {
    LocalDate startDate;
    LocalDate endDate;
}
