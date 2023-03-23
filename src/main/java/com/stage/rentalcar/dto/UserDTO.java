package com.stage.rentalcar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    private boolean isAdmin;
    @Size(min = 8, max = 20)
    private String username;
    @Size(min = 8, max = 20)
    private String password;
}
