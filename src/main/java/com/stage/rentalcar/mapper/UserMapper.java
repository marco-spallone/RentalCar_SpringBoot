package com.stage.rentalcar.mapper;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public List<UserDTO> getUsersDTO(List<User> users){
        return users.stream().map(this::fromEntitytoDTO).collect(Collectors.toList());
    }

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

    public UserDTO fromEntitytoDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
