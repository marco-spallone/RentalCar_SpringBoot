package com.stage.rentalcar.config;

import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomDetailsManager implements UserDetailsService {
    UserService userService;
    BCryptPasswordEncoder passwordEncoder;


    public CustomDetailsManager(UserService userService) {
        this.userService = userService;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(s);
        MyUserDetails myUserDetails = new MyUserDetails();
        if (user != null) {
            myUserDetails.setUsername(s);
            myUserDetails.setId(user.getId());
            myUserDetails.setPassword(passwordEncoder.encode(user.getPassword()));
            myUserDetails.setAdmin(user.isAdmin());
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if(user.isAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
            } else {
                authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
            }
            myUserDetails.setAuthorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return myUserDetails;
    }
}
