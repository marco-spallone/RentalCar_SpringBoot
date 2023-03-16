package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.mapper.UserMapper;
import com.stage.rentalcar.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> getCustomers() {
        return userRepository.getUsersByIsAdmin(false);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User getUserByCredentials(String username, String password) {
        User user = getUserByUsername(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void insOrUpUser(UserDTO userDTO) {
        userRepository.save(userMapper.fromDTOtoEntity(userDTO));
    }

    @Override
    public void delUser(Integer id) {
        userRepository.delete(userRepository.getById(id));
    }
}
