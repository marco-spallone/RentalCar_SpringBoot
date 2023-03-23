package com.stage.rentalcar.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    @Size(min = 8, max = 20)
    String username;
    @NotBlank
    @Size(min = 5, max = 20)
    String password;
}
