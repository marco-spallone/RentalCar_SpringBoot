package com.stage.rentalcar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginResponseDTO {
    @NotBlank
    private String key;
    @NotBlank
    private String value;
    @NotNull
    private boolean admin;
    @NotNull
    private Integer id;

}
