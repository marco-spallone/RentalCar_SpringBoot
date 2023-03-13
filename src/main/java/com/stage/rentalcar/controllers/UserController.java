package com.stage.rentalcar.controllers;

import com.stage.rentalcar.dto.UserDTO;
import com.stage.rentalcar.mapper.UserMapper;
import com.stage.rentalcar.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userMapper.getUsersDTO(userService.getUsers()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(userMapper.fromEntitytoDTO(userService.getUserById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<?> insertOrUpdateUser(@RequestBody UserDTO userDTO){
        userService.insOrUpUser(userDTO);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        userService.delUser(userService.getUserById(id));
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
