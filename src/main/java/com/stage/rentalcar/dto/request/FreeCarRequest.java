package com.stage.rentalcar.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FreeCarRequest {
    @NotNull
    LocalDate startDate;
    @NotNull
    LocalDate endDate;
}
