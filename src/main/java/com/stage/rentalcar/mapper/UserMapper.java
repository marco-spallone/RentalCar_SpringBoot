package com.stage.rentalcar.mapper;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDTOtoEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAdmin(false);
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
