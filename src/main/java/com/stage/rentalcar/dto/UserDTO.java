package com.stage.rentalcar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private boolean isAdmin;
    @NotBlank
    @Size(min = 8, max = 20)
    private String username;
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
}
