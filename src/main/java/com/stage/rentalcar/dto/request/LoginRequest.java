package com.stage.rentalcar.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull
    @Size(min = 8, max = 20)
    String username;
    @NotNull
    @Size(min = 8, max = 20)
    String password;
}
