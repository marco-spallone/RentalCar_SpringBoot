package com.stage.rentalcar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginResponseDTO {
    @NotNull
    private String key;
    @NotNull
    private String value;
    @NotNull
    private boolean admin;
    @NotNull
    private Integer id;

}
