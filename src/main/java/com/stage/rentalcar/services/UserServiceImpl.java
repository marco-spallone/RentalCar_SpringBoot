package com.stage.rentalcar.services;

import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public void insUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delUser(User user) {
        userRepository.delete(user);
    }
}
