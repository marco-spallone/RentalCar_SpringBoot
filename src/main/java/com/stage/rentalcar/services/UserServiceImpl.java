package com.stage.rentalcar.services;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.mapper.UserMapper;
import com.stage.rentalcar.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getCustomers() {
        return userRepository.getUsersByIsAdmin(false);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public void insOrUpUser(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(userMapper.fromDTOtoEntity(userDTO));
        } else {
            edit(userDTO);
        }
    }

    @Override
    public void edit(UserDTO userDTO) {
        User user = getUserById(userDTO.getId());
        if (user != null) {
            userMapper.updateEntity(user, userDTO);
            userRepository.save(user);
        } else {
            throw new RuntimeException("L'entità non esiste");
        }
    }

    @Override
    public void delUser(Integer id) {
        userRepository.delete(userRepository.getById(id));
    }
}
