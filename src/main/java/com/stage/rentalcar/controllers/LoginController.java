package com.stage.rentalcar.controllers;

import com.stage.rentalcar.config.JwtUtils;
import com.stage.rentalcar.dto.LoginResponseDTO;
import com.stage.rentalcar.dto.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> performLogin(@RequestBody LoginRequest loginRequest) {
        if (userDetailsService.loadUserByUsername(loginRequest.getUsername()) != null) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            ResponseEntity response = ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtUtils.generateJwtToken(authentication)).body("");
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setKey("authorization:");
            loginResponseDTO.setValue(response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
            return ResponseEntity.ok().body(loginResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
