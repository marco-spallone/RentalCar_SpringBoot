package com.stage.rentalcar.mapper;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.dto.UserDTONoPass;
import com.stage.rentalcar.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public List<UserDTONoPass> getUsersDTO(List<User> users) {
        return users.stream().map(this::fromEntitytoDTONoPass).collect(Collectors.toList());
    }

    public User fromDTOtoEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAdmin(false);
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTONoPass fromEntitytoDTONoPass(User user) {
        UserDTONoPass userDTONoPass = new UserDTONoPass();
        userDTONoPass.setId(user.getId());
        userDTONoPass.setName(user.getName());
        userDTONoPass.setSurname(user.getSurname());
        userDTONoPass.setUsername(user.getUsername());
        userDTONoPass.setAdmin(user.isAdmin());
        return userDTONoPass;
    }

    public void updateEntity(User user, UserDTO userDTO){
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
    }

}
