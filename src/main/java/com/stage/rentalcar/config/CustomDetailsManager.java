package com.stage.rentalcar.config;

import com.stage.rentalcar.entities.User;
import com.stage.rentalcar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomDetailsManager implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(s);
        MyUserDetails myUserDetails;
        if (user != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (user.isAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
            } else {
                authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
            }
            myUserDetails = MyUserDetails.builder()
                    .id(user.getId()).username(s).password(user.getPassword()).isAdmin(user.isAdmin())
                    .authorities(authorities).build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return myUserDetails;
    }
}
