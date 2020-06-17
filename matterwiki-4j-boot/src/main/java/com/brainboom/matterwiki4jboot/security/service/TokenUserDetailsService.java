package com.brainboom.matterwiki4jboot.security.service;

import com.brainboom.matterwiki4jboot.entity.Users;
import com.brainboom.matterwiki4jboot.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service

public class TokenUserDetailsService implements UserDetailsService {


    @Autowired
    UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> roles = new ArrayList<>();


        Users users = usersRepository.findUsersByEmail(s);
        if (users == null) {
            throw new UsernameNotFoundException("user " + s + " not found");
        }

        if (users.getId() == 1) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(users.getName(), users.getPassword(), roles);
        } else {

            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(users.getName(), users.getPassword(), roles);
        }

    }


}
