package com.stage.rentalcar.controllers;

import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getUsers();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<User> insertOrUpdateUser(@RequestBody User user){
        userService.insOrUpUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.delUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
