package com.stage.rentalcar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTONoPass {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Size(min = 8, max = 20)
    private String username;
    @NotNull
    @Size(min = 8, max = 20)
    private boolean isAdmin;
}
