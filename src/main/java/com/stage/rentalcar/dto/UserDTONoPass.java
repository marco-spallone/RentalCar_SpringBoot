package com.stage.rentalcar.dto;

import lombok.Data;

@Data
public class UserDTONoPass {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private boolean isAdmin;
}
