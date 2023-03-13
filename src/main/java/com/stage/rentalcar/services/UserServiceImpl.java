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
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public void insOrUpUser(UserDTO userDTO) {
        userRepository.saveAndFlush(userMapper.fromDTOtoEntity(userDTO));
    }

    @Override
    public void delUser(User user) {
        userRepository.delete(user);
    }
}
