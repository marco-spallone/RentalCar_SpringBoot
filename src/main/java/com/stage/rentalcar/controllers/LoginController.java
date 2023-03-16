package com.stage.rentalcar.controllers;

import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.request.LoginRequest;
import com.stage.rentalcar.services.UserService;
import com.stage.rentalcar.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> performLogin(@RequestBody LoginRequest loginRequest){
        if(userDetailsService.loadUserByUsername(loginRequest.getUsername())!=null){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtUtils.generateJwtToken(authentication)).body("");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
