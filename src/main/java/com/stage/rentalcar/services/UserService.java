package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Integer id);
    void insOrUpUser(UserDTO userDTO);
    void delUser(User user);
}