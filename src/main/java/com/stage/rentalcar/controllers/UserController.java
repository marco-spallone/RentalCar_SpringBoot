package com.stage.rentalcar.controllers;

import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.repository.UserRepository;
import com.stage.rentalcar.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers(){
        logger.info("get all users");
        List<User> users = userService.getUsers();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
