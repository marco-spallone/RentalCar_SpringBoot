package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.entities.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User getUserById(Integer id);
    public void insOrUpUser(UserDTO userDTO);
    public void delUser(User user);
}
