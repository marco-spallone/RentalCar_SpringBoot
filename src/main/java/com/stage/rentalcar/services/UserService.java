package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.entities.User;

import java.util.List;

public interface UserService {
    List<User> getCustomers();
    User getUserById(Integer id);
    User getUserByUsername(String username);
    User getUserByCredentials(String username, String password);

    void insOrUpUser(UserDTO userDTO);
    void delUser(Integer id);
}